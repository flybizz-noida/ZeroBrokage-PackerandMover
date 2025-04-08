package com.app.zerobrokagepackerandmover.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.ActivityPickupLocationBinding

class PickupLocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPickupLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickupLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvCurrentLocation.setOnClickListener {
            val intent = Intent(this, CurrentLocationActivity::class.java)
            startActivity(intent)
        }

        val number = binding.etSearch.text.toString()
    }
}