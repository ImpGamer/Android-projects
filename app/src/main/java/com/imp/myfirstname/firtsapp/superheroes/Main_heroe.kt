package com.imp.myfirstname.firtsapp.superheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.imp.myfirstname.databinding.ActivityMainHeroeBinding
import com.imp.myfirstname.firtsapp.superheroes.recycler.SuperHeroAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main_heroe : AppCompatActivity() {
    //Creacion de un viewbinding
    private lateinit var binding:ActivityMainHeroeBinding

    //Creacion del adapater para el recyclerView
    private lateinit var adapter:SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.srHeroe.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //Funcion que se ejecuta cuando el usuario presione el boton de buscar en el teclado
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false;
            }
            //Funcion que se ejecuta por cada evento de teclado
            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperHeroAdapter{navigateToDetail(it)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun searchByName(heroe_name:String) {
        //Mostrar el ProgressBar de carga
        binding.progressBar.isVisible = true
        //Funcion que se ejecutara en un hilo secundario al principal (Main)
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse:Response<SuperHeroResponse> = ApiService.getRetrofit().create(ApiService::class.java).getSuperHeroesByName(heroe_name)
            if(myResponse.isSuccessful) {
                val response:SuperHeroResponse? = myResponse.body()
                if(response != null && response.response.lowercase() == "success") {
                    runOnUiThread {
                        adapter.updateList(response.results)
                        binding.progressBar.isVisible = false
                    }
                    Log.i("Data: ","$response")
                }
            }
        }
    }
    private fun getRetrofit():Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(heroe_id:String) {
        val intent = Intent(this,heroe_statics::class.java)
        intent.putExtra(heroe_statics.HEROE_ID,heroe_id)
        startActivity(intent)
    }
}