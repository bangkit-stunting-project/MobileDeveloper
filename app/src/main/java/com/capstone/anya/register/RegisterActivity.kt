package com.capstone.anya.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityRegister2Binding
import com.capstone.anya.databinding.ActivityRegisterBinding
import com.capstone.anya.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.registerButtonNext.setOnClickListener {
            startActivity(Intent(this, Register2Activity::class.java))
        }
    }
}