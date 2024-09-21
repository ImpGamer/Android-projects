package com.imp.myfirstname.firtsapp.TODO

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.imp.myfirstname.R

class TasksViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val txTask:TextView = view.findViewById(R.id.txTask)
    private val checkTask:CheckBox = view.findViewById(R.id.checkTask)

    fun render(task:Task) {
        if(task.isSelected) {
            txTask.paintFlags = txTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            txTask.paintFlags = txTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        checkTask.isChecked = task.isSelected
        val color_box = when(task.category) {
            TaskCategory.Bussiness -> R.color.business_category
            TaskCategory.Other -> R.color.other_category
            TaskCategory.Personal -> R.color.personal_category
        }

        txTask.text = task.name
        checkTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(checkTask.context,color_box)
        )
    }
}