package com.imp.myfirstname.firtsapp.TODO

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.imp.myfirstname.R

class TODO : AppCompatActivity() {
    private val tareas = mutableListOf<Task>(
        Task("Prueba",TaskCategory.Personal),
        Task("Negocios",TaskCategory.Bussiness),
        Task("Other",TaskCategory.Other)
    )
    private val categories = listOf(TaskCategory.Other,TaskCategory.Personal,TaskCategory.Bussiness)
    private lateinit var rvCategories:RecyclerView
    private lateinit var rvTasks:RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var btnAddTask:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponents()
        initUI()
        btnAddTask.setOnClickListener{showDialog()}
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        //Marcar el XML al elemento Dialog
        dialog.setContentView(R.layout.dialog_tasks)
        val task_txt:EditText = dialog.findViewById(R.id.edText)
        val btnAddTaskDialog:Button = dialog.findViewById<Button>(R.id.btnAddTaskDialog)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTaskDialog.setOnClickListener{
            //Me da el ID del elemento seleccionado del grupo de radioButtons
            val selectedID = rgCategories.checkedRadioButtonId
            val selectedRadioButton = rgCategories.findViewById<RadioButton>(selectedID)
            val category_selected:TaskCategory = when(selectedRadioButton.text) {
                getString(R.string.business) -> TaskCategory.Bussiness
                getString(R.string.personal) -> TaskCategory.Personal
                else -> TaskCategory.Other
            }

            addTask(task_txt.text.toString(),category_selected,dialog)
        }

        dialog.show()
    }

    private fun addTask(task_message:String, task_category:TaskCategory,dialog:Dialog) {
        if(task_message.isEmpty() || task_message.isBlank()) {
            AlertDialog.Builder(this).apply {
                setTitle("Tarea vacia")
                setMessage("No has colocado nada en tu tarea.")
                setNeutralButton("Ok",null)
            }.show()
        } else {
            tareas.add(Task(task_message,task_category))
            dialog.hide()
            updateTasks()
        }
    }

    private fun initComponents() {
        rvTasks = findViewById(R.id.rvTasks)
        rvCategories = findViewById(R.id.rvCategories)
        btnAddTask = findViewById(R.id.btnAddTask)
    }

    private fun initUI() {
        //Creacion de un objeto CategoriesAdapter
        categoriesAdapter = CategoriesAdapter(categories) {updateCategories(it)}
        rvCategories.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        //Funcion lambda que permite colocar un evento de click a cada nuevo elemento del TasksAdapter
        tasksAdapter = TasksAdapter(tareas) { position -> onItemSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun onItemSelected(position:Int) {
        tareas[position].isSelected = !tareas[position].isSelected
        updateTasks()
    }

    private fun updateTasks() {
        //Filtrara solo aquellas que tengan el atributo "isSelected" en true
        val selectedCategories:List<TaskCategory> = categories.filter { it.isSelected }
        //Filtrara solamente las categorias que se encuentren seleccionadas
        val newTasks = tareas.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tareas = newTasks

        tasksAdapter.notifyDataSetChanged()
    }
}