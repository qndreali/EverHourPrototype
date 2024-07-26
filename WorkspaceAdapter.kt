package com.mobdeve.s11.santos.andreali.everhourprototype

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceCardBinding

/*  Do not modify any declared variables and methods included in this class.
 *
 *  The WorkspaceAdapter is a custom RecyclerView Adapter class.
 */
class WorkspaceAdapter(private var workspaceItems: ArrayList<Workspace>, private var activity: Activity): RecyclerView.Adapter<WorkspaceAdapter.WorkspaceViewHolder>(){

    // Handles the logic needed for getItemCount().
    override fun getItemCount(): Int {
        return this.workspaceItems.size
    }

    fun getItemPosition(workspaceID: Int): Int {
        return workspaceItems.indexOfFirst { it.workspaceID == workspaceID }
    }

    // Note: This method uses ViewBinding instead of inflating the ViewGroup.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkspaceAdapter.WorkspaceViewHolder {
        val workspaceCardBinding = WorkspaceCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkspaceViewHolder(workspaceCardBinding)
    }

    // Handles binding a model to the ViewHolder.
    override fun onBindViewHolder(holder: WorkspaceAdapter.WorkspaceViewHolder, position: Int) {
        holder.bindPlayListMediaItem(this.workspaceItems[position], position)
    }

    /*  Handles adding a workspace to the stored array list + updates the UI accordingly.
     *  Note: When a workspace item is added, it is added at the end of the RecyclerView.
     */
    fun addWorkspace(workspace: Workspace) {
        this.workspaceItems.add(workspace)
        notifyItemInserted(this.workspaceItems.size - 1)
    }

    /*  Handles removing a workspace item from both the array list and the database. UI is updated
     *  accordingly.
     */
    fun removeWorkspaceItem(position: Int) {
        val workspaceDatabase = WorkspaceDatabase(activity.applicationContext)
        val workspaceItem = workspaceItems[position]
        workspaceDatabase.deleteWorkspace(workspaceItem)

        workspaceItems.removeAt(position)
        notifyItemRemoved(position)
    }

    //  Handles updating a workspace item in the array list + updates the UI accordingly.
    fun updateWorkplaceItem(position: Int, workspace: Workspace) {
        workspaceItems[position] = workspace
        notifyItemChanged(position)
    }

    /*  Do not modify all declared variables and methods included in this class.
     *  WorkspaceViewHolder is an inner class of WorkspaceAdapter responsible for handling the logic
     *  of the ViewHolder. Please note that the class is also an OnClickListener.
     */
    inner class WorkspaceViewHolder(private val workspaceCardBinding: WorkspaceCardBinding): RecyclerView.ViewHolder(workspaceCardBinding.root), View.OnClickListener {
        // To keep track of the position of the ViewHolder in the RecyclerView
        private var myPosition: Int = -1
        // To keep track of the workspace instance bound to the ViewHolder
        private lateinit var item: Workspace

        // Allows for the itemView to trigger the logic in OnClick()
        init {
            this@WorkspaceViewHolder.itemView.setOnClickListener(this)
        }

        /*  Handles binding a workspace item to the ViewHolder. The position of the ViewHolder is also
         *  included for the sake of knowing where to update in the array list.
         */
        fun bindPlayListMediaItem(workspace: Workspace, position: Int) {
            this@WorkspaceViewHolder.myPosition = position
            this@WorkspaceViewHolder.item = workspace

            with(workspaceCardBinding) {
                tvWorkspaceName.text = workspace.name
            }
        }

        /*  When the itemView is clicked on, start the WorkspaceDetailsActivity with the selected workspace item.
         */
        override fun onClick(v: View?) {
            val intent = Intent(activity, WorkspaceDetailsActivity::class.java).apply {
                putExtra("WORKSPACE", item)
            }
            (activity as AppCompatActivity).startActivityForResult(intent, MainActivity.REQUEST_CODE_WORKSPACE_DETAILS)
        }
    }
}
