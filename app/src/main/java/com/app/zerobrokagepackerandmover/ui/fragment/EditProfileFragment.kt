package com.app.zerobrokagepackerandmover.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.app.zerobrokagepackerandmover.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        binding.toolbar.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.tvSave.visibility = View.VISIBLE
        binding.toolbar.tvSave.text = "Save"

        val reasonList = listOf("Not available", "Available")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, reasonList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spGST.adapter = adapter

        binding.spGST.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val reason = reasonList[position]

                if (reason == "Available") {
                    binding.etGstIn.visibility = View.VISIBLE
                    binding.etNameofBusiness.visibility = View.VISIBLE
                    binding.etBusinessAddress.visibility = View.VISIBLE
                } else {
                    binding.etGstIn.visibility = View.GONE
                    binding.etNameofBusiness.visibility = View.GONE
                    binding.etBusinessAddress.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return binding.root
    }
}
