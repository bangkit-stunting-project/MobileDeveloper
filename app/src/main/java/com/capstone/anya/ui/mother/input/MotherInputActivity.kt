package com.capstone.anya.ui.mother.input

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityMotherInputBinding
import com.capstone.anya.ui.mother.monitoring.MotherMonitoringActivity

class MotherInputActivity : AppCompatActivity() {

    private lateinit var motherInputBinding: ActivityMotherInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        title = getString(R.string.title_mother_register)

        super.onCreate(savedInstanceState)
        motherInputBinding = ActivityMotherInputBinding.inflate(layoutInflater)
        setContentView(motherInputBinding.root)

        motherInputBinding.buttonInputIbu.setOnClickListener {
            startActivity(Intent(this, MotherMonitoringActivity::class.java))
            finish()
        }
    }
}