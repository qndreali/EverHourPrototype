package com.mobdeve.s11.santos.andreali.everhourprototype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WorkspaceAdapter() : RecyclerView.Adapter<WorkspaceAdapter.WorkspaceViewHolder>() {

    inner class WorkspaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        override fun onClick(v: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkspaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workspace_card, parent, false)
        return WorkspaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WorkspaceViewHolder, position: Int, model: WorkspaceModel) {
        //holder.bindData(model)
    }
}