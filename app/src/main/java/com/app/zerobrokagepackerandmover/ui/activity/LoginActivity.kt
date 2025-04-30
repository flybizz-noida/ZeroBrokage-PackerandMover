package com.app.zerobrokagepackerandmover.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.ActivityLoginBinding
import com.app.zerobrokagepackerandmover.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPreferences
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        binding.btGetOtp.setOnClickListener {
            val countryCode = binding.countryPeaker.selectedCountryCodeWithPlus
            val mobileNumber = binding.etMobileNumber.text.toString().trim()
            val name = binding.etName.text.toString().trim()

            if (validateInputs(name, mobileNumber, countryCode)) {
                viewModel.sendOtp(name, mobileNumber, countryCode)
            }
        }

        viewModel.loginBaseResponse.observe(this) {
            binding.btGetOtp.isEnabled = true
            if (it.success == true) {
                if (!isFinishing) {
                    val editor = sharedPref.edit()
                    editor.putString("name", binding.etName.text.toString())
                    editor.putString("mobileNumber", binding.etMobileNumber.text.toString())
                    editor.putString("countryCode", binding.countryPeaker.selectedCountryCodeWithPlus)
                    editor.apply()

                    val intent = Intent(this, OtpActivity::class.java)
                    intent.putExtra("name", binding.etName.text.toString())
                    intent.putExtra("mobileNumber", binding.etMobileNumber.text.toString())
                    intent.putExtra("countryCode", binding.countryPeaker.selectedCountryCodeWithPlus)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(name: String, mobileNumber: String, countryCode: String): Boolean {
        if (name.isEmpty()) {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show()
            return false
        }
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
