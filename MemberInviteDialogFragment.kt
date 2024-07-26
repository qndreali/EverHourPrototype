package com.mobdeve.s11.santos.andreali.everhourprototype

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class MemberInviteDialogFragment : DialogFragment() {

    private var onMemberInvitedListener: ((String) -> Unit)? = null

    fun setOnMemberInvitedListener(listener: (String) -> Unit) {
        onMemberInvitedListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.member_invite_ol, null)

        builder.setView(view)
            .setPositiveButton("Invite") { _, _ ->
                val emailEditText: EditText = view.findViewById(R.id.editEmail)
                val email = emailEditText.text.toString()
                onMemberInvitedListener?.invoke(email)
            }
            .setNegativeButton("Cancel", null)

        return builder.create()
    }
}
