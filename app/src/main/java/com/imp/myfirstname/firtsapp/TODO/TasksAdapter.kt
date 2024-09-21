package com.imp.myfirstname.firtsapp.TODO

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imp.myfirstname.R

class TasksAdapter(var tareas:List<Task>,private val onTaskSelected:(Int) -> Unit): RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task,parent,false)
        return TasksViewHolder(view)
    }

    override fun getItemCount(): Int = tareas.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tareas[position])
        holder.itemView.setOnClickListener{onTaskSelected(position)}
        //onTaskSelected(position)
    }
}