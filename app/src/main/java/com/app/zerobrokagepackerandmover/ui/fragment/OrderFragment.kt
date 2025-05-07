package com.app.zerobrokagepackerandmover.ui.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zerobrokagepackerandmover.adapter.LeadsDetailsAdapter
import com.app.zerobrokagepackerandmover.databinding.FragmentOrderBinding
import com.app.zerobrokagepackerandmover.viewModel.LeadsViewModel

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPref: SharedPreferences
    private val leadsViewModel: LeadsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPref = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", 0)

        binding.toolbar.tvHeading.visibility = View.VISIBLE
        binding.toolbar.tvHeading.text = "Orders"

        getApiData(userId)

        return root
    }

    private fun getApiData(userId: Int) {
        binding.rvOrder.layoutManager = LinearLayoutManager(context)
        leadsViewModel.leadsDetails(userId)
        leadsViewModel.leadsBaseResponse.observe(viewLifecycleOwner) { response ->
            response.data.let {
                val leadsList = listOf(it)
                binding.rvOrder.adapter = LeadsDetailsAdapter(leadsList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
