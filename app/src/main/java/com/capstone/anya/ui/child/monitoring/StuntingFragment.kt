package com.capstone.anya.ui.child.monitoring

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.anya.databinding.FragmentStuntingBinding
import com.capstone.anya.ui.child.input.StuntingInputActivity

class StuntingFragment : Fragment() {

    private var _binding: FragmentStuntingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStuntingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnStuntingCheck.setOnClickListener {
            val mIntent = Intent(requireActivity(), StuntingInputActivity::class.java)
            startActivity(mIntent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}