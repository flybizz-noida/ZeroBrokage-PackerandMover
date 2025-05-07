package com.app.zerobrokagepackerandmover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zerobrokagepackerandmover.databinding.BookingCardBinding
import com.app.zerobrokagepackerandmover.model.OrderData

class LeadsDetailsAdapter(private val leadsList: List<OrderData>) :
    RecyclerView.Adapter<LeadsDetailsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BookingCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OrderData) {
            binding.pickupAddress.text = "From: ${item.location_from}"
            binding.dropAddress.text = "To: ${item.location_to}"
            binding.tvTime.text = "Pickup Date & Time: ${item.pickup_date} || ${item.pickup_time}"
            binding.tvPrice.text = "Total: ₹${item.total_amount}"
            binding.statusCancelled.text = "Status: ${item.order_status ?: "Pending"}"
            binding.tvVehicleType.text = "Item: ${item.item_name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BookingCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leadsList[position])
    }

    override fun getItemCount(): Int = leadsList.size
}
