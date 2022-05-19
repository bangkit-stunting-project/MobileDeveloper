package com.capstone.anya.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
    }
}