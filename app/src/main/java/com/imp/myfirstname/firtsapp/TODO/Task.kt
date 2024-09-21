package com.imp.myfirstname.firtsapp.TODO

data class Task(val name:String, val category: TaskCategory, var isSelected:Boolean = false) {
}