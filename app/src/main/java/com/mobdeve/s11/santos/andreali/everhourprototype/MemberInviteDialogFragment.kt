package com.mobdeve.s11.santos.andreali.everhourprototype

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MemberInviteDialogFragment : DialogFragment() {

    interface OnMemberInviteListener {
        fun onMemberInvited(email: String)
    }

    private var listener: OnMemberInviteListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnMemberInviteListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnMemberInviteListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.member_invite_ol, null)

            val editEmail = view.findViewById<TextInputEditText>(R.id.etInvEmail)
            val btnInvite = view.findViewById<Button>(R.id.btnInvite)

            val dialog = builder.setView(view)
                .setCancelable(true)
                .create()

            btnInvite.setOnClickListener {
                val email = editEmail.text.toString()
                if (email.isNotBlank()) {
                    listener?.onMemberInvited(email)
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "Please enter an email", Toast.LENGTH_SHORT).show()
                }
            }

            dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}