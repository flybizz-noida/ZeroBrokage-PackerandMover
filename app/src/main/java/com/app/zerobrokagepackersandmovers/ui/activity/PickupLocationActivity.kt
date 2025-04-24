package com.app.zerobrokagepackersandmovers.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zerobrokagepackersandmovers.RetrofitInstance.RetrofitInstance
import com.app.zerobrokagepackersandmovers.adapter.SuggestionAdapter
import com.app.zerobrokagepackersandmovers.databinding.ActivityPickupLocationBinding
import kotlinx.coroutines.*

class PickupLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPickupLocationBinding
    private lateinit var adapter: SuggestionAdapter
    private var job: Job? = null
    private val apiKey = "ZqO2BstG4Lh4MeY1ybKMUCHBzmE6kv6T0bL2uYv6"

    private var wasSelectionMade = true

    private enum class InputFieldType {
        COUNTRY, STATE, NONE
    }

    private var activeInputField: InputFieldType = InputFieldType.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickupLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val locationText = intent.getStringExtra("current_location")
        locationText?.let {
            binding.etSearchCountry.setText(it)
        }

        setupSuggestionList()
        setupCountrySearchListener()
        setupStateSearchListener()
    }

    private fun setupSuggestionList() {
        adapter = SuggestionAdapter(emptyList()) { selected ->
            when (activeInputField) {
                InputFieldType.COUNTRY -> binding.etSearchCountry.setText(selected)
                InputFieldType.STATE -> binding.etSearchState.setText(selected)
                else -> {}
            }
            wasSelectionMade = false
            binding.rvSuggestion.visibility = View.GONE
            activeInputField = InputFieldType.NONE
        }

        binding.rvSuggestion.layoutManager = LinearLayoutManager(this)
        binding.rvSuggestion.adapter = adapter
    }

    private fun setupCountrySearchListener() {
        binding.etSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s?.toString()?.trim() ?: ""

                if (wasSelectionMade) {
                    wasSelectionMade = false
                }

                if (input.isNotEmpty() && !wasSelectionMade) {
                    activeInputField = InputFieldType.COUNTRY
                    job?.cancel()
                    job = CoroutineScope(Dispatchers.Main).launch {
                        delay(300)
                        fetchSuggestions(input)
                    }
                } else {
                    adapter.updateList(emptyList())
                    binding.rvSuggestion.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupStateSearchListener() {
        binding.etSearchState.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s?.toString()?.trim() ?: ""

                if (wasSelectionMade) {
                    wasSelectionMade = false
                }

                if (input.isNotEmpty() && !wasSelectionMade) {
                    activeInputField = InputFieldType.STATE
                    job?.cancel()
                    job = CoroutineScope(Dispatchers.Main).launch {
                        delay(300)
                        fetchSuggestions(input)
                    }
                } else {
                    adapter.updateList(emptyList())
                    binding.rvSuggestion.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private suspend fun fetchSuggestions(query: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getPlaces(query, apiKey)
                if (response.isSuccessful) {
                    val predictions = response.body()?.predictions ?: emptyList()
                    withContext(Dispatchers.Main) {
                        adapter.updateList(predictions)
                        binding.rvSuggestion.visibility =
                            if (predictions.isNotEmpty()) View.VISIBLE else View.GONE
                    }
                } else {
                    Log.e("API_ERROR", "Response error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Exception: ${e.localizedMessage}")
            }
        }
    }
}
