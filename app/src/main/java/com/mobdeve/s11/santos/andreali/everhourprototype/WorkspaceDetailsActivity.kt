package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceDetailsBinding

class WorkspaceDetailsActivity : AppCompatActivity() {
    private lateinit var binding: WorkspaceDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}