package com.app.zerobrokagepackersandmovers.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.zerobrokagepackersandmovers.MainActivity
import com.app.zerobrokagepackersandmovers.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var countDownTimer: CountDownTimer

    @SuppressLint("SetTextI18n")
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

        binding.tvResend.setOnClickListener {
            startTimer()
        }

        startTimer()
    }

    private fun startTimer() {
        binding.tvResend.isEnabled = false
        countDownTimer = object : CountDownTimer(16000, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / 1000) / 60
                binding.timer.text = String.format("%02d:%02d", minutes, seconds)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.timer.text = "Done!"
                binding.tvResend.isEnabled = true
            }
        }.start()
    }
}
