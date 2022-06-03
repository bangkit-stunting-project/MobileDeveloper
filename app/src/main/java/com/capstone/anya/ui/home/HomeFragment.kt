package com.capstone.anya.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.anya.R
import com.capstone.anya.databinding.FragmentHomeBinding
import com.capstone.anya.ui.listAnak.ListAnakFragment
import com.capstone.anya.ui.monitoring.child.ChildMonitoringActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.monChildWidget.buttonBuatDataAnak.root.setOnClickListener {
            val mIntent = Intent(requireActivity(), ChildMonitoringActivity::class.java)
            startActivity(mIntent)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}