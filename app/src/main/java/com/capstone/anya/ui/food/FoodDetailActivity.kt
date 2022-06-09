package com.capstone.anya.ui.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityChildInputBinding
import com.capstone.anya.databinding.ActivityFoodDetailBinding

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var foodDetailBinding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDetailBinding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(foodDetailBinding.root)

       
    }
}