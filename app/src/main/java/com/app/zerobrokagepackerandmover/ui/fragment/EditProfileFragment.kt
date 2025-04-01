package com.app.zerobrokagepackerandmover.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zerobrokagepackerandmover.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private lateinit  var binding: FragmentEditProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        binding.toolbar.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.toolbar.ivBack.visibility= View.VISIBLE

        binding.toolbar.tvSave.visibility = View.VISIBLE
        binding.toolbar.tvSave.text = "Save"

        return binding.root
    }



}