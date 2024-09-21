package com.imp.myfirstname.firtsapp.superheroes

import com.google.gson.annotations.SerializedName
/*Clase que estructurara el JSON para convertirlo a un objeto Kotlin*/
//En caso que no deseamos llamar a la variable similar que el JSON tenemos la anotacion @SerializedName para darle a que dato va mapeado del JSON
data class SuperHeroResponse(
    @SerializedName("response") val response:String,
    @SerializedName("results") val results:List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val heroId:String,
    @SerializedName("name") val name:String,
    @SerializedName("image") val image:Image
)

data class Image(@SerializedName("url") val url:String)