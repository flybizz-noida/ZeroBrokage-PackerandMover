package com.app.zerobrokagepackersandmovers.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackersandmovers.R
import com.app.zerobrokagepackersandmovers.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.tvSave.visibility = View.VISIBLE
        binding.toolbar.tvSave.text = "Save"

        val reasonList = listOf("Not available", "Available")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, reasonList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spGST.adapter = adapter

        binding.spGST.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val reason = reasonList[position]

                if (reason == "Available") {
                    binding.etGstIn.visibility = View.VISIBLE
                    binding.etNameofBusiness.visibility = View.VISIBLE
                    binding.etBusinessAddress.visibility = View.VISIBLE
                }
                else {
                    binding.etGstIn.visibility = View.GONE
                    binding.etNameofBusiness.visibility = View.GONE
                    binding.etBusinessAddress.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}