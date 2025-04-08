package com.app.zerobrokagepackerandmover.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.zerobrokagepackerandmover.R
import com.app.zerobrokagepackerandmover.databinding.ActivityCurrentLocationBinding
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.model.OlaLatLng
import com.ola.mapsdk.model.OlaMarkerOptions
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrentLocationBinding
    private lateinit var mapView: OlaMapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.mapView.getMap(
            apiKey = "vPTVozNFgPqkmRvpIAiCzuoKZkiKu3HO1Nd7ilDU",
            olaMapCallback = object : OlaMapCallback {

                override fun onMapReady(olaMap: OlaMap) {

                    val vectorDrawable = ContextCompat.getDrawable(
                        this@CurrentLocationActivity,
                        R.drawable.ic_marker
                    )!!
                    val tintedDrawable = DrawableCompat.wrap(vectorDrawable)
                    DrawableCompat.setTint(
                        tintedDrawable,
                        ContextCompat.getColor(this@CurrentLocationActivity, R.color.appcolor)
                    )

                    val bitmap = BitmapUtils.getBitmapFromDrawable(tintedDrawable)

                    val olaPoint = OlaLatLng(28.61666204395904, 77.39012187353822)

                    val markerOptions1 = OlaMarkerOptions.Builder()
                        .setMarkerId("marker1")
                        .setPosition(olaPoint)
                        .setIconBitmap(bitmap!!)
                        .setIconSize(2.5f)
                        .setIsIconClickable(true)
                        .setIconRotation(0f)
                        .setIsAnimationEnable(true)
                        .setIsInfoWindowDismissOnClick(true)
                        .build()

                    val marker1 = olaMap.addMarker(markerOptions1)

                   /* binding.ivZoomin.setOnClickListener {
                        mapView.animateCamera(CameraUpdateFactory.zoomIn())
                    }

                    binding.ivZoomout.setOnClickListener {
                        mapView.animateCamera(CameraUpdateFactory.zoomOut())
                    }*/

                    /*binding.mapOverlay.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_UP) {
                            val screenX = event.x.toInt()
                            val screenY = event.y.toInt()

                            // Convert screen point to OlaLatLng
                            val tappedLatLng = olaMap.getLatLngFromScreenLocation(screenX, screenY)

                            if (tappedLatLng != null) {
                                 marker?.setPosition(tappedLatLng)
                                updateLocationText(tappedLatLng)
                            }
                        }
                        true
                    }*/

                    Handler(Looper.getMainLooper()).postDelayed({
                        olaMap.moveCameraToLatLong(olaPoint, 18.0, 2000)
                    }, 2000)
                }

                override fun onMapError(error: String) {
                    Toast.makeText(this@CurrentLocationActivity, "Server Error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )

    }
}