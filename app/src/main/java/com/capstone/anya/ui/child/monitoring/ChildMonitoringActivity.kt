package com.capstone.anya.ui.child.monitoring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityChildMonitoringBinding
import com.capstone.anya.ui.child.input.StuntingInputActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ChildMonitoringActivity : AppCompatActivity() {

    private lateinit var bindingChildMonitoring: ActivityChildMonitoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingChildMonitoring = ActivityChildMonitoringBinding.inflate(layoutInflater)
        setContentView(bindingChildMonitoring.root)

        title = getString(R.string.title_child_monitoring)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayShowHomeEnabled(true)

        bindingChildMonitoring.btnStuntingCheck.setOnClickListener {
            val mIntent = Intent(this, StuntingInputActivity::class.java)
            startActivity(mIntent)
        }

//        setupTabLayout()
    }

//    private fun setupTabLayout(){
//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
//    }
//
    companion object {
//        @StringRes
//        private val TAB_TITLES = intArrayOf(
//            R.string.title_food,
//            R.string.title_Nutrition,
//            R.string.title_stunting,
//
//        )
        const val EXTRA_CHILD = "extra_child"

    }
}