package com.capstone.anya.ui.listAnak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.capstone.anya.R
import com.capstone.anya.ui.inputAnak.InputAnakActivity

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.navInputAnak -> {
                val menuInputAnak = Intent(this, InputAnakActivity::class.java)
                startActivity(menuInputAnak)
                return true
            }
            else -> return true
        }
    }
}