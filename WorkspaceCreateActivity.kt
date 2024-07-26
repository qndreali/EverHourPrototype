package com.mobdeve.s11.santos.andreali.everhourprototype

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceCreateBinding

class WorkspaceCreateActivity : AppCompatActivity() {

    private lateinit var binding: WorkspaceCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateWS.setOnClickListener {
            val workspaceName = binding.etvWorkspaceName.text.toString()
            if (workspaceName.isNotEmpty()) {
                val newWorkspace = Workspace(workspaceName, 0, 0)
                val resultIntent = Intent().apply {
                    putExtra("NEW_WORKSPACE", newWorkspace)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        val ivHome: ImageView = findViewById(R.id.ivHome)
        ivHome.setOnClickListener {
            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
