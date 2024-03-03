package com.example.reminders.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reminders.R
import com.example.reminders.databinding.ActivityAddReminderBinding
import com.example.reminders.databinding.ActivityMainBinding

class AddReminder : AppCompatActivity() {

    private lateinit var binding : ActivityAddReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        binding.buttonSubmit.setOnClickListener{
            saveTheReminder();
            intentBackToMainScreen();
        }
    }

    private fun saveTheReminder() {

    }

    private fun intentBackToMainScreen() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}