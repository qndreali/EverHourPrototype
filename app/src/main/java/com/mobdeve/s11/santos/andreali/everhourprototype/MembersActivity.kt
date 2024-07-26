package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.MembersOverviewBinding

class MembersActivity : AppCompatActivity() {
    private lateinit var binding: MembersOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MembersOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}