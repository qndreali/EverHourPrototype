package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.MembersOverviewBinding

class MembersActivity : AppCompatActivity() {

    private lateinit var binding: MembersOverviewBinding
    private lateinit var workspace: Workspace
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MembersOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        // Retrieve the workspace from the intent
        val workspaceData = intent.getSerializableExtra("WORKSPACE")
        if (workspaceData != null && workspaceData is Workspace) {
            workspace = workspaceData

            // Load and display members
            val members = dbHelper.getMembers(workspace.workspaceID)

            if (members.isEmpty()) {
                showInviteMemberDialog()
            } else {
                // Initialize RecyclerView
                val adapter = MembersAdapter(members)
                binding.rvMembers.layoutManager = LinearLayoutManager(this)
                binding.rvMembers.adapter = adapter
            }
        } else {
            Log.e("MembersActivity", "Workspace data is missing or not of expected type.")
            finish() // Optionally finish the activity or show an error message
        }

        binding.btnInvite.setOnClickListener {
            showInviteMemberDialog()
        }
    }

    private fun showInviteMemberDialog() {
        val inviteDialog = MemberInviteDialogFragment()
        inviteDialog.setOnMemberInvitedListener { email ->
            val roleDialog = MemberRoleDialogFragment(email)
            roleDialog.setOnRoleSetListener { role ->
                inviteMember(email, role)
            }
            roleDialog.show(supportFragmentManager, "MemberRoleDialogFragment")
        }
        inviteDialog.show(supportFragmentManager, "MemberInviteDialogFragment")
    }

    private fun inviteMember(email: String, role: String) {
        dbHelper.addMember(workspace.workspaceID, email, role)

        // Refresh members list after adding
        refreshMembersList()
    }

    private fun refreshMembersList() {
        val members = dbHelper.getMembers(workspace.workspaceID)
        val adapter = MembersAdapter(members)
        binding.rvMembers.adapter = adapter
    }
}
