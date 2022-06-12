package com.capstone.anya.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.capstone.anya.R
import com.capstone.anya.api.ResponseEducationItem
import com.capstone.anya.databinding.FragmentHomeBinding
import com.capstone.anya.login.dataStore
import com.capstone.anya.main.ViewModelFactory
import com.capstone.anya.model.UserPreference
import com.capstone.anya.ui.child.list.ChildListActivity
import com.capstone.anya.ui.education.EducationDetailActivity
import com.capstone.anya.ui.education.EducationFragment
import com.capstone.anya.ui.education.EducationViewModel
import com.capstone.anya.ui.mother.input.MotherInputActivity

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val educationViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
        )[EducationViewModel::class.java]

        val homeEducationAdapter = HomeEducationAdapter()

        educationViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        educationViewModel.educationList.observe(viewLifecycleOwner) {
            homeEducationAdapter.setEducation(it)
            binding.rvEducation.apply {
                adapter = homeEducationAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
            }
            homeEducationAdapter.setOnItemClickCallback(object : HomeEducationAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ResponseEducationItem) {
                    intentEducationDetail(data)
                }
            })
        }

        educationViewModel.getToken().observe(viewLifecycleOwner) {
            educationViewModel.getEducation(it.token.toString())
        }

        setupAction()
        binding.monMotherWidget.buttonBuatDataIbu.root.setOnClickListener {
            val mIntent = Intent(requireActivity(), MotherInputActivity::class.java)
            startActivity(mIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAction() {
        binding.monChildWidget.buttonBuatDataAnak.root.setOnClickListener {
            val mIntent = Intent(requireActivity(), ChildListActivity::class.java)
            startActivity(mIntent)
        }

        binding.tvEducationView.setOnClickListener {
            val mEducationFragment = EducationFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_home, mEducationFragment, EducationFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun intentEducationDetail(education: ResponseEducationItem) {
        val educationDetailIntent = Intent(requireActivity(), EducationDetailActivity::class.java)
        educationDetailIntent.putExtra(EducationDetailActivity.EXTRA_EDUCATION, education)
        startActivity(educationDetailIntent)
    }
}