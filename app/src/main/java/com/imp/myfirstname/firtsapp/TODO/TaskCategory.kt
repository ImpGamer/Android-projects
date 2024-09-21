package com.imp.myfirstname.firtsapp.TODO

sealed class TaskCategory(var isSelected:Boolean = true) {
    object Personal: TaskCategory()
    object Bussiness: TaskCategory()
    object Other: TaskCategory()
}
