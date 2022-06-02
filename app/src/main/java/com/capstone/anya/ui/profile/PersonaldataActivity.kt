package com.capstone.anya.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityMainBinding
import com.capstone.anya.databinding.ActivityPersonaldataBinding

class PersonaldataActivity : AppCompatActivity() {

    private lateinit var bindingPersonalData: ActivityPersonaldataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPersonalData = ActivityPersonaldataBinding.inflate(layoutInflater)
        setContentView(bindingPersonalData.root)


        bindingPersonalData.textChangeNamePersonal.setOnClickListener {

        }
    }
}