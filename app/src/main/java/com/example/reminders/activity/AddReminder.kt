package com.example.reminders.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reminders.databinding.ActivityAddReminderBinding
import com.example.reminders.utils.DBHelper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddReminder : AppCompatActivity() {

    private lateinit var binding : ActivityAddReminderBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        binding.buttonSubmit.setOnClickListener{
            saveTheReminder()
            intentBackToMainScreen()
        }
        binding.btnSelect.setOnClickListener{
            showDatePicker()

        }
    }


    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this, {_, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                binding.tvSelectedDate.text = "$formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        showTimePicker()
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            binding.tvSelectedTime.text = SimpleDateFormat("HH:mm:ss").format(cal.time)
        }
        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    private fun saveTheReminder() {
        val db = DBHelper(this)

        val title = binding.editTextTaskTitle.text.toString()
        val date = binding.tvSelectedDate.text.toString()
        val time = binding.tvSelectedTime.text.toString()


        db.addReminder(title,date,time)

        Toast.makeText(this, "$title added to database", Toast.LENGTH_SHORT).show()

    }

    private fun intentBackToMainScreen() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}