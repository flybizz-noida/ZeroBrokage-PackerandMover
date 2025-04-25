package com.app.zerobrokagepackersandmovers.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.zerobrokagepackersandmovers.MainActivity
import com.app.zerobrokagepackersandmovers.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mobileNumber = intent.getStringExtra("mobileNumber")
        val countryCode = intent.getStringExtra("countryCode")

        binding.tvTextMobileNumber.text = "$countryCode $mobileNumber"


        binding.btVerify.setOnClickListener {

            val otp = binding.otpView.otp.toString().trim()
            if (otp.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
