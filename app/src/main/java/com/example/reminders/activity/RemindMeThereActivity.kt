package com.example.reminders.activity

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.reminders.R

import com.example.reminders.databinding.ActivityRemindMeThereBinding


class RemindMeThereActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRemindMeThereBinding
    private var latitude = 0.0
    private var longitude = 0.0
    private var address = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remind_me_there)

        init()
    }

    private fun init(){
        binding.buttonSubmit.setOnClickListener {

        }
        binding.btnSelectLocation.setOnClickListener {
            showLocationPickerDialog()
        }
    }

    private fun showLocationPickerDialog() {
        val intent = Intent(this, LocationPickerActivity::class.java)
        locationPickerActivityResultLauncher.launch(intent)
    }

    private val locationPickerActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                val data = result.data

                if(data != null){
                    latitude = data.getDoubleExtra("latitude", 0.0)
                    longitude = data.getDoubleExtra("lontitude", 0.0)
                    address = data.getStringExtra("address") ?: ""

                    Log.d("","locationPickerActivityResultLauncher: latitude: $latitude")
                    Log.d("","locationPickerActivityResultLauncher: longitude: $longitude")
                    Log.d("","locationPickerActivityResultLauncher: address: $address")

                    binding.tvSelectedLocation.setText(address)
                }

            } else {
                Log.d("","locationPickerActivityResultLauncher: Cancelled")
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}