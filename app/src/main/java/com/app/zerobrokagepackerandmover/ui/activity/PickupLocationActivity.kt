package com.app.zerobrokagepackerandmover.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zerobrokagepackerandmover.RetrofitInstance.RetrofitInstance
import com.app.zerobrokagepackerandmover.adapter.SuggestionAdapter
import com.app.zerobrokagepackerandmover.databinding.ActivityPickupLocationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PickupLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPickupLocationBinding
    private lateinit var adapter: SuggestionAdapter
    private var job: Job? = null
    private val apiKey = "ZqO2BstG4Lh4MeY1ybKMUCHBzmE6kv6T0bL2uYv6"

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

        setupSuggestionList()
        setupSearchListener()
    }

    private fun setupSuggestionList() {
        adapter = SuggestionAdapter(emptyList()) { selected ->
            binding.etSearchCountry.setText(selected)
            binding.rvSuggestion.visibility = View.GONE
        }
        binding.rvSuggestion.layoutManager = LinearLayoutManager(this)
        binding.rvSuggestion.adapter = adapter
    }

    private fun setupSearchListener() {
        binding.etSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s?.toString()?.trim() ?: ""
                if (input.length >= 1) {
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
