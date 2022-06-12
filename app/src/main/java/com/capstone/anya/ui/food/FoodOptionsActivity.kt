package com.capstone.anya.ui.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityFoodOptionsBinding
import com.capstone.anya.databinding.ActivityRegisterBinding
import com.capstone.anya.login.LoginActivity

class FoodOptionsActivity : AppCompatActivity() {

    private lateinit var foodOptionsBinding: ActivityFoodOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodOptionsBinding = ActivityFoodOptionsBinding.inflate(layoutInflater)
        setContentView(foodOptionsBinding.root)

        foodOptionsBinding.buttonPilihMakanan.setOnClickListener {
            startActivity(Intent(this, FoodSearchActivity::class.java))
            finish()
        }

    }
}