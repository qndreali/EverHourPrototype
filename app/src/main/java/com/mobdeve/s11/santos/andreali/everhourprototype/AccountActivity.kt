package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.AccountSettingsBinding

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: AccountSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}