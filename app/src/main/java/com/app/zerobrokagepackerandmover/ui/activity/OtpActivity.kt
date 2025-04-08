package com.app.zerobrokagepackerandmover.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.zerobrokagepackerandmover.MainActivity
import com.app.zerobrokagepackerandmover.databinding.ActivityOtpBinding

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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}