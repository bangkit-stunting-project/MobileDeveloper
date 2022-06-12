package com.capstone.anya.ui.child.monitoring

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.anya.databinding.FragmentFoodBinding
import com.capstone.anya.ui.food.FoodOptionsActivity


class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnAddFood.setOnClickListener {
            val mIntent = Intent(requireActivity(), FoodOptionsActivity::class.java)
            startActivity(mIntent)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}