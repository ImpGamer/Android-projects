package com.imp.myfirstname.firtsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.imp.myfirstname.R

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var txtResult:TextView
    private lateinit var txtIMC:TextView
    private lateinit var txtDescription:TextView
    private lateinit var btnRecalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result:Double = intent.extras?.getDouble(CalculatorIMCActivity.IMC)?:-1.0
        initComponents()
        initUI(result)
        btnRecalculate.setOnClickListener{
            onBackPressed()
        }
    }


    private fun initComponents() {
        txtResult = findViewById(R.id.txtResult)
        txtIMC = findViewById(R.id.txtIMC)
        txtDescription = findViewById(R.id.txtDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
    private fun initUI(result:Double) {
        txtIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                txtResult.text = getString(R.string.bajo_peso)
                txtResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                txtDescription.text = getString(R.string.descripcion_bajo_peso)
            }
            in 18.51..24.99 -> { //Peso normal
                txtResult.text = getString(R.string.normal_peso)
                txtResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                txtDescription.text = getString(R.string.descripcion_normal_peso)
            }
            in 25.00..29.99 -> { //SobrePeso
                txtResult.text = getString(R.string.sobre_peso)
                txtResult.setTextColor(ContextCompat.getColor(this,R.color.sobrepeso))
                txtDescription.text = getString(R.string.descripcion_sobre_peso)
            }
            in 30.00..99.00 -> { //Obesidad
                txtResult.text = getString(R.string.obesidad_peso)
                txtResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                txtDescription.text = getString(R.string.descripcion_obesidad_peso)
            }
            else -> { //error
                txtResult.text = getString(R.string.error)
                txtIMC.text = getString(R.string.error)
                txtDescription.text = getString(R.string.error)
                txtResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
            }
        }
    }
}