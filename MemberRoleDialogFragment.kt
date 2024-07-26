package com.mobdeve.s11.santos.andreali.everhourprototype

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class MemberRoleDialogFragment(private val email: String) : DialogFragment() {

    private var onRoleSetListener: ((String) -> Unit)? = null

    fun setOnRoleSetListener(listener: (String) -> Unit) {
        onRoleSetListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.member_role_ol, null)

        builder.setView(view)
            .setPositiveButton("Set Role") { _, _ ->
                val roleEditText: EditText = view.findViewById(R.id.editRole)
                val role = roleEditText.text.toString()
                onRoleSetListener?.invoke(role)
            }
            .setNegativeButton("Cancel", null)

        return builder.create()
    }
}
