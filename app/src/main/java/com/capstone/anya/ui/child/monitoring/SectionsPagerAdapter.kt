package com.capstone.anya.ui.child.monitoring

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FoodFragment()
            1 -> fragment = NutritionFragment()
            2 -> fragment = StuntingFragment()
        }
        return fragment as Fragment
    }

}