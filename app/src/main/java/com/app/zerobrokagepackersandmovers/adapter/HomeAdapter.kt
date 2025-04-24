package com.app.zerobrokagepackersandmovers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zerobrokagepackersandmovers.data.HomeItems
import com.app.zerobrokagepackersandmovers.databinding.ServicesLayoutBinding

class HomeAdapter(private val itemList: List<HomeItems>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(val binding: ServicesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ServicesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.ivIcon.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int = itemList.size
}
