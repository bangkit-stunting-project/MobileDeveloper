package com.capstone.anya.ui.mother.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.databinding.ActivityMotherInputBinding
import com.capstone.anya.ui.mother.monitoring.MotherMonitoringActivity

class MotherInputActivity : AppCompatActivity() {

    private lateinit var motherInputBinding: ActivityMotherInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        motherInputBinding = ActivityMotherInputBinding.inflate(layoutInflater)
        setContentView(motherInputBinding.root)

        motherInputBinding.buttonInputIbu.setOnClickListener {
            startActivity(Intent(this, MotherMonitoringActivity::class.java))
            finish()
        }
    }
}