package com.app.zerobrokagepackerandmover.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.FragmentPaymentBinding
import com.app.zerobrokagepackerandmover.databinding.FragmentPickupBinding

class PickupFragment : Fragment() {
    private var _binding: FragmentPickupBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickupBinding.inflate(inflater, container, false)

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return binding.root
    }



}