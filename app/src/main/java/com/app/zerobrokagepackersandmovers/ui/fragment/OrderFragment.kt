package com.app.zerobrokagepackersandmovers.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.zerobrokagepackersandmovers.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.toolbar.tvHeading.visibility = View.VISIBLE
        binding.toolbar.tvHeading.text = "Orders"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}