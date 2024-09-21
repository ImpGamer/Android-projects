package com.imp.myfirstname.firtsapp.superheroes

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name")val name:String,
    @SerializedName("powerstats") val powerStats:PowerStats,
    @SerializedName("image") val image:Image,
    @SerializedName("biography") val biography: Biography
)

data class PowerStats(
    @SerializedName("intelligence")val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String
)

data class Biography(
    @SerializedName("full-name") val fullName:String,
    @SerializedName("publisher") val publisher:String
)
