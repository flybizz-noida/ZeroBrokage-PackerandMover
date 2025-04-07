package com.app.zerobrokagepackerandmover.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.FragmentProfileBinding
import com.app.zerobrokagepackerandmover.ui.activity.EditProfileActivity
import com.app.zerobrokagepackerandmover.ui.activity.LanguageActivity


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
            val intent = Intent(requireActivity(), LanguageActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}
