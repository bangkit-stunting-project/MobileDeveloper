package com.capstone.anya.ui.education

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.anya.R
import com.capstone.anya.api.ResponseEducationItem
import com.capstone.anya.databinding.ActivityEducationDetailBinding
import com.capstone.anya.utils.formatDate
import java.util.*

class EducationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationDetailBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        title = getString(R.string.title_education_detail)

        super.onCreate(savedInstanceState)
        binding = ActivityEducationDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val educationDetail = intent.getParcelableExtra<ResponseEducationItem>(EXTRA_EDUCATION)

        if (educationDetail != null) {
            binding.tvTitleEducation.text = educationDetail.title
            Glide.with(binding.photo)
                .load(educationDetail.urlToImage)
                .apply(RequestOptions.placeholderOf(R.drawable.banner_anya_2).error(R.drawable.banner_anya_2))
                .into(binding.photo)
            binding.tvChildDate.text = formatDate(educationDetail.publishedAt, TimeZone.getDefault().id)
            binding.tvDescription.text = educationDetail.content
        }
    }

    companion object {
        const val EXTRA_EDUCATION = "extra_education"
    }

}