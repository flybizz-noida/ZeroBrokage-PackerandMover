package com.app.zerobrokagepackersandmovers.ui.activity


import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackersandmovers.R
import com.app.zerobrokagepackersandmovers.databinding.ActivityCurrentLocationBinding
import com.google.android.gms.location.*
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView
import java.util.Locale

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrentLocationBinding
    private lateinit var mapView: OlaMapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var olaMapInstance: OlaMap? = null

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.ivBack.setOnClickListener { finish() }

        binding.tvLocation

       /* binding.mapView.getMap(
            apiKey = "ZqO2BstG4Lh4MeY1ybKMUCHBzmE6kv6T0bL2uYv6",
            olaMapCallback = object : OlaMapCallback {
                override fun onMapReady(olaMap: OlaMap) {
                    olaMapInstance = olaMap
                    checkLocationPermissionAndFetch()
                }

                override fun onMapError(error: String) {
                    Toast.makeText(this@CurrentLocationActivity, "Server Error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )*/
    }

    /* private fun checkLocationPermissionAndFetch() {
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
             getCurrentLocation()
         } else {
             ActivityCompat.requestPermissions(
                 this,
                 arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                 LOCATION_PERMISSION_REQUEST_CODE
             )
         }
     }*/

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    /* private fun getCurrentLocation() {
         fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
             if (location != null) {
                 showMarkerOnMap(location.latitude, location.longitude)
                 val address = getAddressFromLatLng(location.latitude, location.longitude)
                 binding.tvLocation.text = address
             } else {
                 val locationRequest = LocationRequest.create().apply {
                     priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                     interval = 1000
                     fastestInterval = 500
                     numUpdates = 1
                 }

                 val locationCallback = object : LocationCallback() {
                     override fun onLocationResult(locationResult: LocationResult) {
                         val newLocation = locationResult.lastLocation
                         if (newLocation != null) {
                             showMarkerOnMap(newLocation.latitude, newLocation.longitude)
                             val address = getAddressFromLatLng(newLocation.latitude, newLocation.longitude)
                             binding.tvLocation.text = address
                         } else {
                             Toast.makeText(this@CurrentLocationActivity, "Unable to fetch location", Toast.LENGTH_SHORT).show()
                         }
                         fusedLocationClient.removeLocationUpdates(this)
                     }
                 }

                 fusedLocationClient.requestLocationUpdates(
                     locationRequest,
                     locationCallback,
                     Looper.getMainLooper()
                 )
             }
         }.addOnFailureListener {
             Toast.makeText(this, "Failed to get location", Toast.LENGTH_SHORT).show()
         }
     }*/

    private fun getAddressFromLatLng(lat: Double, lng: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses: MutableList<Address>? = geocoder.getFromLocation(lat, lng, 1)
            if (addresses?.isNotEmpty() == true) {
                val address = addresses.get(0)
                return "${address.getAddressLine(0)}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "Unable to get address"
    }

    /*@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation()
        } else {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }*/
}
