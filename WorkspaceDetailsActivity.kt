package com.mobdeve.s11.santos.andreali.everhourprototype

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceDetailsBinding

class WorkspaceDetailsActivity : AppCompatActivity() {

    private lateinit var binding: WorkspaceDetailsBinding
    private lateinit var workspace: Workspace
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        workspace = intent.getSerializableExtra("WORKSPACE") as Workspace

        binding.tvWorkspaceDetails.text = workspace.name
        binding.etWorkspaceName.setText(workspace.name)
        binding.tvTrackedNum.text = workspace.hours.toString()
        binding.tvCountNum.text = workspace.projects.toString()

        setupUI()
    }

    private fun setupUI() {
        binding.ivEdit.setOnClickListener {
            binding.tvWorkspaceDetails.visibility = View.GONE
            binding.etWorkspaceName.visibility = View.VISIBLE
            binding.btnSave.visibility = View.VISIBLE
        }

        binding.btnSave.setOnClickListener {
            val updatedName = binding.etWorkspaceName.text.toString()

            // Create new Workspace instance with updated name
            val updatedWorkspace = Workspace(
                workspace.workspaceID,
                updatedName,
                workspace.hours,
                workspace.projects
            )

            // Save changes to the database
            val values = ContentValues().apply {
                put(DatabaseHelper.WORKSPACE_NAME, updatedName)
            }
            dbHelper.writableDatabase.update(
                DatabaseHelper.WORKSPACE_TABLE,
                values,
                "${DatabaseHelper.WORKSPACE_ID} = ?",
                arrayOf(workspace.workspaceID.toString())
            )

            // Update the UI
            binding.tvWorkspaceDetails.text = updatedName
            binding.tvWorkspaceDetails.visibility = View.VISIBLE
            binding.etWorkspaceName.visibility = View.GONE
            binding.btnSave.visibility = View.GONE

            val resultIntent = Intent().apply {
                putExtra("UPDATED_WORKSPACE", updatedWorkspace)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnMembers.setOnClickListener {
            if (hasMembers(workspace.workspaceID)) {
                showMembersList()
            } else {
                showInviteMemberDialog()
            }
        }
    }

    private fun showInviteMemberDialog() {
        val inviteDialog = MemberInviteDialogFragment()
        inviteDialog.setOnMemberInvitedListener { email ->
            val roleDialog = MemberRoleDialogFragment(email)
            roleDialog.setOnRoleSetListener { role ->
                addMember(email, role)
            }
            roleDialog.show(supportFragmentManager, "MemberRoleDialogFragment")
        }
        inviteDialog.show(supportFragmentManager, "MemberInviteDialogFragment")
    }

    private fun hasMembers(workspaceId: Int): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.MEMBERS_TABLE,
            null,
            "${DatabaseHelper.WORKSPACE_ID} = ?",
            arrayOf(workspaceId.toString()),
            null,
            null,
            null
        )
        val hasMembers = cursor.count > 0
        cursor.close()
        return hasMembers
    }

    private fun addMember(email: String, role: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.WORKSPACE_ID, workspace.workspaceID)
            put(DatabaseHelper.MEMBER_NAME, email)
            put(DatabaseHelper.MEMBER_ROLE, role)
        }
        db.insert(DatabaseHelper.MEMBERS_TABLE, null, values)
    }

    private fun showMembersList() {
        val intent = Intent(this, MembersActivity::class.java)
        intent.putExtra("WORKSPACE", workspace)  // Pass the whole workspace object
        startActivity(intent)
    }
}
