package com.capstone.anya.ui.child.list

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.capstone.anya.api.ResponseChildListItem
import com.capstone.anya.databinding.ChildListItemBinding
import com.capstone.anya.utils.formatDate
import java.util.*

class ChildListAdapter: RecyclerView.Adapter<ChildListAdapter.ChildListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var childList = mutableListOf<ResponseChildListItem>()

    class ChildListViewHolder(val binding: ChildListItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun setChild(childList: List<ResponseChildListItem>) {
        this.childList = childList.toMutableList()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChildListItemBinding.inflate(inflater, parent, false)
        return ChildListViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ChildListViewHolder, position: Int) {
        val child = childList[position]
        holder.binding.tvChildName.text = child.namaLengkap
        holder.binding.tvChildDate.text = formatDate(child.tanggalLahir, TimeZone.getDefault().id)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(childList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResponseChildListItem)
    }
}

