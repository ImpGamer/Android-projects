package com.imp.myfirstname.firtsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.imp.myfirstname.R
import com.imp.myfirstname.firtsapp.settings.Settings
import com.imp.myfirstname.firtsapp.superheroes.Main_heroe

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)
        val btnCalculatorIMC = findViewById<Button>(R.id.btnIMCApp)
        val btnTODO = findViewById<Button>(R.id.btnTODO)
        val btnHeroes = findViewById<Button>(R.id.btnHeroes)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        btnSaludar.setOnClickListener{ navigateToSaludarApp() }
        btnCalculatorIMC.setOnClickListener{ navigateToCalculatorIMC() }
        btnTODO.setOnClickListener{navigateToTODO()}
        btnHeroes.setOnClickListener{navigateToSuperHeroeAPP()}
        btnSettings.setOnClickListener{navigateToSettings()}
    }

    private fun navigateToSaludarApp() {
        val intent = Intent(this,FirstAppActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToCalculatorIMC(){
        val intent = Intent(this,CalculatorIMCActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToTODO() {
        val intent = Intent(this, com.imp.myfirstname.firtsapp.TODO.TODO::class.java)
        startActivity(intent)
    }
    private fun navigateToSuperHeroeAPP() {
        val intent = Intent(this,Main_heroe::class.java)
        startActivity(intent)
    }
    private fun navigateToSettings() {
        val intent = Intent(this,Settings::class.java)
        startActivity(intent)
    }

}