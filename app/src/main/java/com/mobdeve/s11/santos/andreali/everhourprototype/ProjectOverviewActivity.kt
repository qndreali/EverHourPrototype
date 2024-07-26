package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.ProjectOverviewBinding

class ProjectOverviewActivity : AppCompatActivity() {
    private lateinit var binding: ProjectOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProjectOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}