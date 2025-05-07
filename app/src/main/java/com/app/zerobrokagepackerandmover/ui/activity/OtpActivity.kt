package com.app.zerobrokagepackerandmover.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.zerobrokagepackerandmover.MainActivity
import com.app.zerobrokagepackerandmover.databinding.ActivityOtpBinding
import com.app.zerobrokagepackerandmover.viewModel.LoginViewModel
import kotlin.getValue

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val mobileNumber = intent.getStringExtra("mobileNumber")
        val countryCode = intent.getStringExtra("countryCode")

        binding.tvTextMobileNumber.text = "$countryCode $mobileNumber"

        binding.btVerify.setOnClickListener {
            val otp = binding.pinView.text.toString().trim()
            if (otp.length == 4 || !countryCode.isNullOrEmpty() && !mobileNumber.isNullOrEmpty()) {
                if (!countryCode.isNullOrEmpty() && !mobileNumber.isNullOrEmpty()) {
                    viewModel.verifyOtp(mobileNumber, countryCode, otp)

                }
            } else {
                Toast.makeText(this, "Please enter a valid 4-digit OTP.", Toast.LENGTH_SHORT).show()

            }

            viewModel.loginBaseResponse.observe(this) { response ->
                if (response.success) {
                    sharedPref.edit()
                        .putBoolean("isLoggedIn", true)
                        .putInt("userId", response.data.user_id)
                        .apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "OTP Verification Failed", Toast.LENGTH_SHORT).show()
                }
            }

           /* binding.tvResend.setOnClickListener {
                startTimer()
            }*/

          //  startTimer()
        }

    }

    /*private fun startTimer() {
        binding.tvResend.isEnabled = false
        countDownTimer = object : CountDownTimer(16000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / 1000) / 60
                binding.timer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.timer.text = "Done!"
                binding.tvResend.isEnabled = true
            }
        }.start()
    }*/
}