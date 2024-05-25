package com.example.reminders.activity

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reminders.R
import com.example.reminders.databinding.ActivityLocationPickerBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.PlacesStatusCodes
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class LocationPickerActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityLocationPickerBinding

    private var mMap: GoogleMap? = null
    private var mPlaceClient: PlacesClient? = null
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null


    private var mLastKnownLocation: Location? = null
    private var selectedLatitude: Double? = null
    private var selectedLongitude: Double? = null
    private var selectedAddress = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneBtn.visibility = View.GONE

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        Places.initialize(this, getString(R.string.api))

        mPlaceClient = Places.createClient(this)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val autocompleteSupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
        val placesList =
            arrayOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)

        autocompleteSupportMapFragment.setPlaceFields(listOf(*placesList))

        autocompleteSupportMapFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                TODO("Not yet implemented")
            }

            override fun onPlaceSelected(place: Place) {
                Log.d("LocationPickerActivity", "onPlaceSelected: ")
                val id = place.id
                val name = place.name
                val latLng = place.latLng
                selectedLatitude = latLng?.latitude
                selectedLongitude = latLng?.longitude

                selectedAddress = place.address ?: ""

                addMarker(latLng, title, selectedAddress)
            }
        })

        binding.toolbarBackBtn.setOnClickListener {
            onBackPressed()
        }
        
        binding.toolbarGpdBtn.setOnClickListener { 
            if(isGpsEnabled()){
                requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                Toast.makeText(this,"turn on the location",Toast.LENGTH_SHORT).show()
            }
        }

    }

    @SuppressLint("MissingPermission")
    private val requestLocationPermission: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted ->

            if(isGranted){
                mMap!!.isMyLocationEnabled = true
                pickCurrentPlace()
            } else {
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
            }

        }

    private fun isGpsEnabled(): Boolean {

    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        mMap!!.setOnMapClickListener { latLng ->
            selectedLongitude = latLng.longitude
            selectedLatitude = latLng.latitude

            addressFromLatLng(latLng)
        }

    }

    private fun addressFromLatLng(latLng: LatLng) {
        
    }
}
