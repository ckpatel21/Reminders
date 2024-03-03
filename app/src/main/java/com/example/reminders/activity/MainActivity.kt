package com.example.reminders.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminders.R
import com.example.reminders.adapter.TaskAdapter
import com.example.reminders.databinding.ActivityMainBinding
import com.example.reminders.model.Task

class MainActivity : AppCompatActivity() {

    private lateinit var taskList : ArrayList<Task>
    private lateinit var taskAdapter : TaskAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.button.setOnClickListener{
            addNewTask();
        }
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

    private fun taskListItems(){
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
        taskList.add(Task("chicken Pizza","12","12"))
    }

}