package com.app.zerobrokagepackersandmovers.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackersandmovers.R
import com.app.zerobrokagepackersandmovers.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btGetOtp.setOnClickListener {
            val countryCode = binding.countryPeaker.selectedCountryCodeWithPlus
            val mobileNumber = binding.etMobileNumber.text.toString()

            if (validateInputs(mobileNumber, countryCode)) {
                // binding.btGetOtp.isEnabled = false


                val intent = Intent(this, OtpActivity::class.java)
                intent.putExtra("mobileNumber", mobileNumber)
                intent.putExtra("countryCode", countryCode)
                startActivity(intent)

            }
        }


    }
    private fun validateInputs(mobileNumber : String, countryCode: String): Boolean {

        if (mobileNumber.length != 10 || !mobileNumber.all { it.isDigit() }) {
            Toast.makeText(this, "Please enter a valid mobile number.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (countryCode.isEmpty() || !countryCode.startsWith("+")) {
            Toast.makeText(this, "Please select a valid country code.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}


