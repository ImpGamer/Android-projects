package com.imp.myfirstname.firtsapp.superheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.imp.myfirstname.R
import com.imp.myfirstname.databinding.ActivityHeroeStaticsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class heroe_statics : AppCompatActivity() {
    companion object {
        const val HEROE_ID = "heroeID"
    }
    private lateinit var binding:ActivityHeroeStaticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroeStaticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val heroe_id:String = intent.getStringExtra(HEROE_ID).orEmpty()
        getHeroStatics(heroe_id)
    }

    private fun getHeroStatics(id:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = ApiService.getRetrofit().create(ApiService::class.java).getSuperHeroeByID(id)
            if(superHeroDetail.body() != null && superHeroDetail.isSuccessful) {
                runOnUiThread{createUI(superHeroDetail.body()!!)}
            }
        }
    }

    private fun createUI(heroeDetails:SuperHeroDetailResponse) {
        Picasso.get().load(heroeDetails.image.url).into(binding.imgHero)
        binding.txtHeroName.text = heroeDetails.name
        binding.txtRealName.text = heroeDetails.biography.fullName
        binding.txtPublisher.text = heroeDetails.biography.publisher
        setUIStats(heroeDetails.powerStats)
    }
    private fun setUIStats(powerStats: PowerStats) {
        //Extraer los parametros de nuestra View para colocarle en el height del View
        binding.viewCombat.layoutParams.height = pxToDp(powerStats.combat.toInt())
        binding.viewDurability.layoutParams.height = pxToDp(powerStats.durability.toInt())
        binding.viewIntelligence.layoutParams.height = pxToDp(powerStats.intelligence.toInt())
        binding.viewPower.layoutParams.height = pxToDp(powerStats.power.toInt())
        binding.viewSpeed.layoutParams.height = pxToDp(powerStats.speed.toInt())
        binding.viewStrenght.layoutParams.height = pxToDp(powerStats.strength.toInt())

        binding.viewCombat.requestLayout()
        binding.viewDurability.requestLayout()
        binding.viewIntelligence.requestLayout()
        binding.viewPower.requestLayout()
        binding.viewSpeed.requestLayout()
        binding.viewStrenght.requestLayout()
    }
    private fun pxToDp(px:Int):Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px.toFloat(),resources.displayMetrics).toInt()
    }
}