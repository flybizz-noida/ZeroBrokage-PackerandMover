package com.app.zerobrokagepackersandmovers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.zerobrokagepackersandmovers.olaModel.Prediction

class SuggestionAdapter(
    private var predictions: List<Prediction>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>() {

    inner class SuggestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSuggestion: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return SuggestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        val prediction = predictions[position]
        holder.tvSuggestion.text = prediction.description

        holder.itemView.setOnClickListener {
            onItemClick(prediction.description)
        }
    }

    override fun getItemCount(): Int = predictions.size

    fun updateList(newList: List<Prediction>) {
        predictions = newList
        notifyDataSetChanged()
    }
}
