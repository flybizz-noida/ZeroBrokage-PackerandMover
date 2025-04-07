package com.app.zerobrokagepackerandmover.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.ActivityCurrentLocationBinding
import com.ola.mapsdk.view.OlaMap

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrentLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*binding.mapView.getMap(
            apiKey = "<aD1-ZUocSjjJWFcISULB>",
            olaMapCallback = object : OlaMapCallback {
                override fun onMapReady(olaMap: OlaMap) {
                    Toast.makeText(this@CurrentLocationActivity, "Open Map", Toast.LENGTH_SHORT).show()
                }

                override fun onMapError(error: String) {
                    Toast.makeText(this@CurrentLocationActivity, "Not Open", Toast.LENGTH_SHORT).show()
                }
            }
        )*/


    }
}