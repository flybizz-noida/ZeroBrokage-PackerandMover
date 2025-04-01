package com.app.zerobrokagepackerandmover.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.tvEditProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)


        }

        binding.tvLogout.setOnClickListener {
            val logoutBottomSheet = LogoutBottomFragment()
            logoutBottomSheet.show(parentFragmentManager, "LogoutBottomSheet")
        }

        return binding.root
    }
}
