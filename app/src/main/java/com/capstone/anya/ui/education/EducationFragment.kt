package com.capstone.anya.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.capstone.anya.databinding.FragmentEducationBinding
import com.capstone.anya.login.dataStore
import com.capstone.anya.main.ViewModelFactory
import com.capstone.anya.model.UserPreference

class EducationFragment : Fragment() {

    private var _binding: FragmentEducationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val educationViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
        )[EducationViewModel::class.java]

        val educationAdapter = EducationAdapter()

        educationViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        educationViewModel.getToken().observe(viewLifecycleOwner) {
            educationViewModel.getEducation(it.token.toString())
        }

        educationViewModel.educationList.observe(viewLifecycleOwner) {
            educationAdapter.setEducation(it)
            binding.rvEducation.apply {
                adapter = educationAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}