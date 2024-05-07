package com.example.reminders.activity

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reminders.R

import com.example.reminders.databinding.ActivityRemindMeThereBinding


class RemindMeThereActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRemindMeThereBinding


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

    }
}