package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceOverviewBinding

class WorkspaceActivity : AppCompatActivity() {

    private lateinit var binding: WorkspaceOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}