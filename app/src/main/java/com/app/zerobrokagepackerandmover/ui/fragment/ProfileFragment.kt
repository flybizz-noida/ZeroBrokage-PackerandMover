package com.app.zerobrokagepackerandmover.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.zerobrokagepackerandmover.databinding.FragmentProfileBinding
import com.app.zerobrokagepackerandmover.ui.activity.EditProfileActivity
import com.app.zerobrokagepackerandmover.ui.activity.LoginActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPref = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.tvEditProfile.setOnClickListener {
            val intent = Intent(requireActivity(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.tvLogout.setOnClickListener {
            sharedPref.edit().apply {
                clear()
                putBoolean("isLoggedIn", false)
                apply()
            }

            val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            startActivity(intent)
            requireActivity().finish()
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
