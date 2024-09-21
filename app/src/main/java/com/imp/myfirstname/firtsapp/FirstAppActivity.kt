package com.imp.myfirstname.firtsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.imp.myfirstname.R

class FirstAppActivity : AppCompatActivity() {
    //Funcion que se llama al llamar a esta pantalla de la aplicacion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)
        val btnStart = findViewById<AppCompatButton>(R.id.btnStart);
        val etName = findViewById<AppCompatEditText>(R.id.etName);

        btnStart.setOnClickListener {
            val name:String = etName.text.toString()

            if(name.isNotEmpty()) {
                val intent = Intent(this,ResultActivity::class.java)
                //Manera de mandar datos hacia otro componente
                intent.putExtra("NAME",name)
                startActivity(intent)
            }
        }
    }
}