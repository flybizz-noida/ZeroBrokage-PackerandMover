package com.app.zerobrokagepackersandmovers.ui.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zerobrokagepackersandmovers.R
import com.app.zerobrokagepackersandmovers.adapter.HomeAdapter
import com.app.zerobrokagepackersandmovers.data.HomeItems
import com.app.zerobrokagepackersandmovers.databinding.FragmentHomeBinding
import com.app.zerobrokagepackersandmovers.ui.activity.PickupLocationActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                if (
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    getCurrentLocation()
                }
            } else {
                binding.tvLocation.text = "Permission denied"
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        checkLocationPermissionAndFetch()

        binding.cdLocation.setOnClickListener {
            val locationText = binding.tvLocation.text.toString()
            val intent = Intent(requireActivity(), PickupLocationActivity::class.java)
            intent.putExtra("current_location", locationText)
            startActivity(intent)
        }

        val homeItems = listOf(
            HomeItems("Service 1", R.drawable.hindi),
            HomeItems("Service 2", R.drawable.english),
            HomeItems("Service 3", R.drawable.english),
            HomeItems("Service 4", R.drawable.hindi),
        )

        val adapter = HomeAdapter(homeItems)
        binding.rvServices.layoutManager = GridLayoutManager(context,2)
        binding.rvServices.adapter = adapter


        return binding.root
    }

    private fun checkLocationPermissionAndFetch() {
        if (
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getCurrentLocation()
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            binding.tvLocation.text = "Permission not granted"
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0].getAddressLine(0)
                    binding.tvLocation.text = address
                } else {
                    binding.tvLocation.text = "Unable to get address"
                }
            } ?: run {

                binding.tvLocation.text = "Location not available"
            }
        }
        
        binding.cdBookingStatus.btnView.setOnClickListener {
            Toast.makeText(context, "View", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
