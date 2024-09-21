package com.imp.myfirstname.firtsapp.TODO

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.imp.myfirstname.R

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)
    private val viewContainer:CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemsSelected: (Int) -> Unit) {
        val color = if(taskCategory.isSelected) {
            R.color.background_card
        } else {
            R.color.background_disabled
        }
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))
        itemView.setOnClickListener{ onItemsSelected(layoutPosition)}

        when(taskCategory) {
            TaskCategory.Bussiness -> {
                tvCategoryName.text = "Negocios"
                //Cualquier componente posee el contexto asi que podemos reutilizar el divider
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.business_category))
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.other_category))
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.personal_category))
            }
        }
    }
}