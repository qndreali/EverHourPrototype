package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.ProjectDetailsBinding

class ProjectDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ProjectDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}