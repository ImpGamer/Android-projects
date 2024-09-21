package com.imp.myfirstname.firtsapp.superheroes.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imp.myfirstname.R
import com.imp.myfirstname.firtsapp.superheroes.SuperHeroItemResponse

class SuperHeroAdapter(var superheroItems:List<SuperHeroItemResponse> = emptyList(),
                       private val onItemSelected:(String) -> Unit):RecyclerView.Adapter<SuperHeroViewHolder>() {
    fun updateList(superheroItems:List<SuperHeroItemResponse>) {
        this.superheroItems = superheroItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superheroe,parent,false))
    }

    override fun getItemCount(): Int = superheroItems.size

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superheroItems[position],onItemSelected)
    }
}