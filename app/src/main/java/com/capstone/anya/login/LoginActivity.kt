package com.capstone.anya.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityLoginBinding
import com.capstone.anya.databinding.ActivityRegister2Binding
import com.capstone.anya.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.registerButtonNavigation.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        supportActionBar?.hide()
    }
}