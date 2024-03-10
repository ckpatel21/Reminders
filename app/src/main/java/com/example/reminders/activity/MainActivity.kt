package com.example.reminders.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminders.adapter.TaskAdapter
import com.example.reminders.databinding.ActivityMainBinding
import com.example.reminders.model.Task
import com.example.reminders.utils.DBHelper

class MainActivity : AppCompatActivity() {

    private lateinit var taskList : ArrayList<Task>
    private lateinit var taskAdapter : TaskAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        dbHelper = DBHelper(this)

        init()

        binding.button.setOnClickListener{
            addNewTask();
        }
    }

    private fun createNotificationChannel() {
        val name : CharSequence = "ReminderChannel"
        val description = "Channel for Alarm Manager"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("remindersApp",name,importance)
        channel.description = description
        val notificationManager = getSystemService(
            NotificationManager::class.java
        )

        notificationManager.createNotificationChannel(channel)
    }

    private fun addNewTask() {
        val intent = Intent(this,AddReminder::class.java)
        startActivity(intent)
    }

    private fun init(){
        bindTheRecyclerView()
    }

    private fun bindTheRecyclerView() {
        taskList = ArrayList()
        taskAdapter = TaskAdapter(taskList)
        taskListItems()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = taskAdapter
    }

    private fun taskListItems() {

        // Fetch data from the database using DBHelper
        val cursor = dbHelper.getAllReminders()

        // Clear existing data
        taskList.clear()

        // Check if the cursor is not null before proceeding
        cursor?.let {
            // Get column indices
            val titleIndex = it.getColumnIndex(DBHelper.Title_COl)
            val dateIndex = it.getColumnIndex(DBHelper.Date_COL)
            val timeIndex = it.getColumnIndex(DBHelper.Time_COL)

            // Iterate through the cursor and add reminders to the list
            while (it.moveToNext()) {
                // Check if column indices are valid
                if (titleIndex != -1 && dateIndex != -1 && timeIndex != -1) {
                    val title = it.getString(titleIndex) ?: "DefaultTitle"
                    val date = it.getString(dateIndex) ?: "DefaultDate"
                    val time = it.getString(timeIndex) ?: "DefaultTime"

                    taskList.add(Task(title, date, time))
                }
            }

            // Close the cursor
            it.close()
        }
        // Update the adapter with the new data
        taskAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        dbHelper.close()
    }

}