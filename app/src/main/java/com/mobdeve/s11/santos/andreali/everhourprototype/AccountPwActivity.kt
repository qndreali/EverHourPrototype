package com.mobdeve.s11.santos.andreali.everhourprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.AccountEditBinding
import com.mobdeve.s11.santos.andreali.everhourprototype.databinding.AccountPasswordBinding

class AccountPwActivity : AppCompatActivity() {
    private lateinit var binding: AccountPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}