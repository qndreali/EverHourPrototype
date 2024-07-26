package com.mobdeve.s11.santos.andreali.everhourprototype

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout

class WorkspaceUpdateDialogFragment(private val workspace: Workspace) : DialogFragment() {

    interface OnWorkspaceUpdatedListener {
        fun onWorkspaceUpdated(updatedWorkspace: Workspace)
    }

    private var listener: OnWorkspaceUpdatedListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.workspace_update_ol, null)

        builder.setView(view)

        val tvUpdate: TextView = view.findViewById(R.id.tvUpdate)
        val tilWorkspaceName: TextInputLayout = view.findViewById(R.id.tilWorkspaceName)
        val btnSet: Button = view.findViewById(R.id.btnSet)

        tvUpdate.text = "Update ${workspace.name}"

        // Populate the current workspace name in the EditText
        val editText: EditText? = tilWorkspaceName.editText
        editText?.setText(workspace.name)

        btnSet.setOnClickListener {
            val updatedName = editText?.text.toString()

            if (updatedName.isNotBlank()) {
                val updatedWorkspace = Workspace(workspace.workspaceID, updatedName, workspace.hours, workspace.projects)
                listener?.onWorkspaceUpdated(updatedWorkspace)
                dismiss()
            } else {
                // Handle empty name case (e.g., show a warning message)
                tilWorkspaceName.error = "Workspace name cannot be empty"
            }
        }

        return builder.create()
    }

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        listener = if (context is OnWorkspaceUpdatedListener) {
            context
        } else {
            throw RuntimeException("$context must implement OnWorkspaceUpdatedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
