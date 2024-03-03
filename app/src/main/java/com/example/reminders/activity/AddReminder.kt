package com.example.reminders.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reminders.databinding.ActivityAddReminderBinding
import com.example.reminders.utils.DBHelper

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
        val db = DBHelper(this)

        val title = binding.editTextTaskTitle.text.toString()
        val date = binding.editTextReminderDate.text.toString()
        val time = binding.editTextReminderTime.text.toString()

        db.addReminder(title,date,time)

        Toast.makeText(this, "$title added to database", Toast.LENGTH_SHORT).show()

    }

    private fun intentBackToMainScreen() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}