package com.app.zerobrokagepackerandmover.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.ActivityCurrentLocationBinding
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.model.OlaLatLng
import com.ola.mapsdk.model.OlaMarkerOptions
import com.ola.mapsdk.view.OlaMap
import java.util.Locale

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrentLocationBinding
    private var olaMap: OlaMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { finish() }

        if (checkLocationPermission()) {
            loadMap()
        } else {
            requestLocationPermission()
        }
    }

    private fun loadMap() {
        if (!isLocationEnabled()) {
            Toast.makeText(this, "Please enable location", Toast.LENGTH_SHORT).show()
            return
        }

        binding.mapView.getMap("vPTVozNFgPqkmRvpIAiCzuoKZkiKu3HO1Nd7ilDU", object : OlaMapCallback {


            override fun onMapReady(map: OlaMap) {
                olaMap = map

                Handler(Looper.getMainLooper()).postDelayed({
                    val location = olaMap?.getCurrentLocation()
                    if (location != null) {
                        val currentLatLng = OlaLatLng(location.latitude, location.longitude, 0.0)

                        val drawable = ContextCompat.getDrawable(this@CurrentLocationActivity, R.drawable.ic_marker)
                        val bitmap = BitmapUtils.getBitmapFromDrawable(drawable!!)
                        val markerOptions = OlaMarkerOptions.Builder()
                            .setMarkerId("current")
                            .setPosition(currentLatLng)
                            .setIconBitmap(bitmap!!)
                            .build()

                        olaMap?.addMarker(markerOptions)
                        olaMap?.moveCameraToLatLong(currentLatLng, 15.0, 2000)

                        binding.tvLocation.text = getAddressFromLatLng(currentLatLng.latitude, currentLatLng.longitude)
                    } else {
                        Toast.makeText(this@CurrentLocationActivity, "Unable to get current location", Toast.LENGTH_SHORT).show()
                    }
                }, 3000)
            }
            override fun onMapError(error: String) {
                Toast.makeText(this@CurrentLocationActivity, "Map error: $error", Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun getAddressFromLatLng(lat: Double, lng: Double): String {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lng, 1)
            addresses?.firstOrNull()?.getAddressLine(0) ?: "$lat, $lng"
        } catch (e: Exception) {
            "$lat, $lng"
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadMap()
        } else {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
