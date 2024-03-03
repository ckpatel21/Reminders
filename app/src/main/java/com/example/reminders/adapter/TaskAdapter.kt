package com.example.reminders.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminders.R
import com.example.reminders.model.Task

class TaskAdapter(private val taskLists: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val taskTitle : TextView = itemView.findViewById(R.id.taskTitle)
        val taskDate : TextView = itemView.findViewById(R.id.tvDate)
        val taskTime : TextView = itemView.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.task_item,
            parent,false)
        return TaskViewHolder(viewLayout)
    }

    override fun getItemCount() = taskLists.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = taskLists[position]
        holder.taskTitle.text = currentTask.taskTitle
        holder.taskDate.text = currentTask.taskDate
        holder.taskTime.text = currentTask.taskTime
    }
}

