package com.capstone.anya.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.anya.databinding.ActivityPersonaldataBinding

class PersonalDataActivity : AppCompatActivity() {

    private lateinit var bindingPersonalData: ActivityPersonaldataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPersonalData = ActivityPersonaldataBinding.inflate(layoutInflater)
        setContentView(bindingPersonalData.root)


        bindingPersonalData.textChangeNamePersonal.setOnClickListener {

        }
    }
}