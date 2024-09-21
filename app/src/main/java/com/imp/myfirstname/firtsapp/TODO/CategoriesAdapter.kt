package com.imp.myfirstname.firtsapp.TODO

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imp.myfirstname.R

//Clase que nos permite crear cada componente reutilizable y pintarlo en la pantalla
class CategoriesAdapter(private val categories:List<TaskCategory>,private val onItemsSelected:(Int) -> Unit):RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category,parent,false)
        return CategoriesViewHolder(view)
    }
    //Funcion que retorna la cantidad de datos que posee la clase
    override fun getItemCount(): Int = categories.size

    //Funcion que se ejecuta al renderizar cada elemento del RecyclerView
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position],onItemsSelected)
        
    }
}