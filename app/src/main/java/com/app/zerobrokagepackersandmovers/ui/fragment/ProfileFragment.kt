package com.app.zerobrokagepackersandmovers.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.zerobrokagepackersandmovers.databinding.FragmentProfileBinding
import com.app.zerobrokagepackersandmovers.ui.activity.EditProfileActivity


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.tvEditProfile.setOnClickListener {
            val intent = Intent(requireActivity(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.tvLogout.setOnClickListener {
            val logoutBottomSheet = LogoutBottomFragment()
            logoutBottomSheet.show(parentFragmentManager, "LogoutBottomSheet")
        }

        binding.tvAddGST.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.tvLanguage.setOnClickListener {
            val logoutBottomSheet = ChooseTruckFragment()
            logoutBottomSheet.show(parentFragmentManager, "ChooseTruckFragment")
        }

        binding.tvAppVersion.text = "App Version 1.0"

        return binding.root
    }
}
