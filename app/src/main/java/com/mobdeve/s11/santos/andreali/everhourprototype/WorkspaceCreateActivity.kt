package com.mobdeve.s11.santos.andreali.everhourprototype

import Workspace
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceCreateBinding

class WorkspaceCreateActivity : AppCompatActivity() {

    private lateinit var binding: WorkspaceCreateBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference

        // Ensure currentUser is not null
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val userId = currentUser.uid

        binding.btnCreateWS.setOnClickListener {
            val workspaceName = binding.etvWorkspaceName.text.toString().trim()

            if (workspaceName.isNotEmpty()) {
                val workspaceId = dbRef.child("workspaces").child(userId).push().key
                if (workspaceId == null) {
                    Toast.makeText(this, "Error generating workspace ID", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val workspace = Workspace(
                    id = workspaceId,
                    name = workspaceName,
                    hours = 0,  // Default or initial value
                    projectsCount = 0  // Default or initial value
                )

                dbRef.child("workspaces").child(userId).child(workspaceId).setValue(workspace)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Workspace created successfully!", Toast.LENGTH_SHORT).show()
                        finish()  // Close the activity and return to the previous one
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to create workspace.", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please enter a workspace name.", Toast.LENGTH_SHORT).show()
            }
            Log.d("WorkspaceCreateActivity", "Button clicked")
        }
    }
}