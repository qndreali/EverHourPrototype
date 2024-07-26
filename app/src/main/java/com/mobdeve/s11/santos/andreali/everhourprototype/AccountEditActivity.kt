package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.AccountEditBinding

class AccountEditActivity : AppCompatActivity() {
    private lateinit var binding: AccountEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}