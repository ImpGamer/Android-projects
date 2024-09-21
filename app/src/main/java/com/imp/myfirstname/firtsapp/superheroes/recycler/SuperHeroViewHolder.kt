package com.imp.myfirstname.firtsapp.superheroes.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.imp.myfirstname.databinding.ItemSuperheroeBinding
import com.imp.myfirstname.firtsapp.superheroes.SuperHeroItemResponse
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroeBinding.bind(view)

    fun bind(superHeroItem:SuperHeroItemResponse,onItemSelected:(String) -> Unit) {
        binding.txtHeroe.text = superHeroItem.name
        //Colocar imagenes de URL dentro de un ImageView
        Picasso.get().load(superHeroItem.image.url).into(binding.imgHeroe)
        binding.root.setOnClickListener{onItemSelected(superHeroItem.heroId)}
    }
}