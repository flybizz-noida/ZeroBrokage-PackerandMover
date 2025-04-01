package com.app.zerobrokagepackerandmover.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.FragmentHomeBinding
import com.app.zerobrokagepackerandmover.databinding.FragmentLogoutBottomBinding
import com.app.zerobrokagepackerandmover.ui.activity.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LogoutBottomFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentLogoutBottomBinding? = null
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
        _binding = FragmentLogoutBottomBinding.inflate(inflater, container, false)

        binding.tvSure.text = "Are you sure you want to logout?"
        binding.btYes.setOnClickListener {
            val intent = Intent(it.context, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btNo.setOnClickListener {
            dismiss()
        }

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}