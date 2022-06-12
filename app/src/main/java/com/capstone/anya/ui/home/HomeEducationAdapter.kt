package com.capstone.anya.ui.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.anya.R
import com.capstone.anya.api.ResponseEducationItem
import com.capstone.anya.databinding.EducationItemBinding
import com.capstone.anya.utils.formatDate
import java.util.*

class HomeEducationAdapter: RecyclerView.Adapter<HomeEducationAdapter.EducationViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var educationList = mutableListOf<ResponseEducationItem>()

    class EducationViewHolder(val binding: EducationItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun setEducation(education: List<ResponseEducationItem>) {
        this.educationList = education.toMutableList()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EducationItemBinding.inflate(inflater, parent, false)
        return EducationViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val education = educationList[position]
        Glide.with(holder.itemView.context)
            .load(education.urlToImage)
            .apply(RequestOptions.placeholderOf(R.drawable.banner_anya_1).error(R.drawable.banner_anya_1))
            .into(holder.binding.photo)
        holder.binding.tvTitle.text = education.title
        holder.binding.tvTime.text = formatDate(education.publishedAt, TimeZone.getDefault().id)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(educationList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResponseEducationItem)
    }
}

