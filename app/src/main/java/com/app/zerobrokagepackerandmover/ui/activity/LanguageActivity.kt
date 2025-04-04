package com.app.zerobrokagepackerandmover.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.zerobrokagepackerandmover.MainActivity
import com.app.zerobrokagepackerandmover.databinding.ActivityLanguageBinding
import com.app.zerobrokagepackerandmover.utils.setAppLocale

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedLanguage = getSavedLanguage(this)
        binding.rbHindi.isChecked = selectedLanguage == "hi"
        binding.rbEnglish.isChecked = selectedLanguage == "en"

        binding.cardEnglish.setOnClickListener {
            binding.rbEnglish.isChecked = true
            binding.rbHindi.isChecked = false
            saveLanguagePreference(this, "en")
        }

        binding.cardHindi.setOnClickListener {
            binding.rbHindi.isChecked = true
            binding.rbEnglish.isChecked = false
            saveLanguagePreference(this, "hi")
        }

        binding.btnContinue.setOnClickListener {
            val selectedLang = if (binding.rbHindi.isChecked) "hi" else "en"
            saveLanguagePreference(this, selectedLang)
            setAppLocale(selectedLang)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun saveLanguagePreference(context: Context, lang: String) {
        val sharedPref = context.getSharedPreferences("language_pref", Context.MODE_PRIVATE)
        sharedPref.edit().putString("language", lang).apply()
    }

    private fun getSavedLanguage(context: Context): String {
        val sharedPref = context.getSharedPreferences("language_pref", Context.MODE_PRIVATE)
        return sharedPref.getString("language", "en") ?: "en"
    }
}
