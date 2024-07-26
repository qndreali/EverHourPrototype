package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.WorkspaceOverviewBinding

class WorkspaceOverviewActivity : AppCompatActivity() {

    private lateinit var binding: WorkspaceOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WorkspaceOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}