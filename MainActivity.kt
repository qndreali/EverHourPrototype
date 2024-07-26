package com.mobdeve.s11.santos.andreali.everhourprototype

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceOverviewBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_WORKSPACE_DETAILS = 200
        const val CREATE_WORKSPACE_REQUEST_CODE = 100
    }

    private lateinit var binding: WorkspaceOverviewBinding
    private lateinit var workspaceAdapter: WorkspaceAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val workspaceDatabase = WorkspaceDatabase(applicationContext)
        workspaceAdapter = WorkspaceAdapter(workspaceDatabase.getWorkspace(), this)
        binding.rvWorkspaces.layoutManager = LinearLayoutManager(this)
        binding.rvWorkspaces.adapter = workspaceAdapter

        binding.btnCreateWs.setOnClickListener {
            val intent = Intent(this, WorkspaceCreateActivity::class.java)
            startActivityForResult(intent, CREATE_WORKSPACE_REQUEST_CODE)
        }

        val swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.workspaceAdapter = workspaceAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvWorkspaces)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CREATE_WORKSPACE_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    val newWorkspace = data?.getSerializableExtra("NEW_WORKSPACE") as? Workspace
                    if (newWorkspace != null) {
                        val workspaceDatabase = WorkspaceDatabase(applicationContext)
                        workspaceDatabase.addWorkspace(newWorkspace)

                        workspaceAdapter.addWorkspace(newWorkspace)
                        binding.rvWorkspaces.smoothScrollToPosition(workspaceAdapter.itemCount - 1)
                    }
                }
            }
            REQUEST_CODE_WORKSPACE_DETAILS -> {
                if (resultCode == RESULT_OK) {
                    val updatedWorkspace = data?.getSerializableExtra("UPDATED_WORKSPACE") as? Workspace
                    if (updatedWorkspace != null) {
                        val workspaceDatabase = WorkspaceDatabase(applicationContext)
                        workspaceDatabase.updateWorkspace(updatedWorkspace)

                        val position = workspaceAdapter.getItemPosition(updatedWorkspace.workspaceID)
                        if (position != -1) {
                            workspaceAdapter.updateWorkplaceItem(position, updatedWorkspace)
                        }
                    }
                }
            }
        }
    }
}
