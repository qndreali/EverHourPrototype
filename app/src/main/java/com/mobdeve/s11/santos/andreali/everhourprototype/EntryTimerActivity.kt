package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.EntryOverviewBinding
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.EntryTimerBinding

class EntryTimerActivity : AppCompatActivity() {
    private lateinit var binding: EntryTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntryTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}