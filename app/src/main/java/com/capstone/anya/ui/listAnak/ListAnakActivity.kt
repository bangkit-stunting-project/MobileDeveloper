package com.capstone.anya.ui.listAnak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.capstone.anya.R

class ListAnakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_anak)

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_anak, menu)
        return true
    }
}