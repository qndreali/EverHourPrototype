package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.EntryOverviewBinding

class EntryOverviewActivity : AppCompatActivity() {
    private lateinit var binding: EntryOverviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntryOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}