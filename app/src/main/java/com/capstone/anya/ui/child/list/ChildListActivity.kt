package com.capstone.anya.ui.child.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.capstone.anya.R
import com.capstone.anya.ui.child.input.InputAnakActivity

class ChildListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_list)

        title = getString(R.string.title_list_anak)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_anak, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.navInputAnak -> {
                val menuInputAnak = Intent(this, InputAnakActivity::class.java)
                startActivity(menuInputAnak)
                true
            }
            else -> true
        }
    }
}