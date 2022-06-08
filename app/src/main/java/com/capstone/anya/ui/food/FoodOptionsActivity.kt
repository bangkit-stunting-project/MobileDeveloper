package com.capstone.anya.ui.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityFoodOptionsBinding
import com.capstone.anya.databinding.ActivityRegisterBinding

class FoodOptionsActivity : AppCompatActivity() {

    private lateinit var foodOptionsBinding: ActivityFoodOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodOptionsBinding = ActivityFoodOptionsBinding.inflate(layoutInflater)
        setContentView(foodOptionsBinding.root)



    }
}