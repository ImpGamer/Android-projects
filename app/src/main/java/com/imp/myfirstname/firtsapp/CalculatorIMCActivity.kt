package com.imp.myfirstname.firtsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.imp.myfirstname.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

class CalculatorIMCActivity : AppCompatActivity() {
    companion object {
        const val IMC = "IMC_RESULT"
    }
    private var isSelected:Boolean = true;
    private var maleActive:Boolean = true
    private var femaleActive:Boolean = false

    private lateinit var viewMale:CardView;
    private lateinit var viewFemale:CardView;
    private lateinit var heightSlider:RangeSlider
    private lateinit var heightValueTxt:TextView

    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var btnPlusWeight:FloatingActionButton
    private lateinit var btnSubstractAge:FloatingActionButton
    private lateinit var btnSubstractWeight:FloatingActionButton
    private lateinit var btnCalculate:Button

    private lateinit var txtAge:TextView
    private lateinit var txtWeight:TextView

    private var ageValue:Int =0;
    private var weightValue:Int =0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_imcactivity)
        initComponents()

         ageValue = 20
         weightValue = 40

        viewMale.setOnClickListener{
            setActive(isSelected, it as CardView, viewFemale)
            maleActive = !femaleActive
        }
        viewFemale.setOnClickListener {
            setActive(!isSelected, it as CardView, viewMale)
            femaleActive = !maleActive
        }
        heightSlider.addOnChangeListener{ _,value,_ ->
            heightValueTxt.text = "${value.roundToInt()} cm"
        }
        btnSubstractAge.setOnClickListener{
            ageValue = subtractValue(ageValue,1)
            txtAge.text = ageValue.toString()
        }
        btnPlusAge.setOnClickListener{
            ageValue = addValue(ageValue,100)
            txtAge.text = ageValue.toString()
        }
        btnPlusWeight.setOnClickListener{
            weightValue = addValue(weightValue,200)
            txtWeight.text = weightValue.toString()
        }
        btnSubstractWeight.setOnClickListener{
            weightValue = subtractValue(weightValue,35)
            txtWeight.text = weightValue.toString()
        }
        btnCalculate.setOnClickListener{
            val result:Double = calculate()
            try {
                val intent = Intent(this,ResultIMCActivity::class.java)
                intent.putExtra(IMC,result)
                startActivity(intent)
            }catch(e:Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        heightSlider = findViewById(R.id.heightSlider)
        heightValueTxt = findViewById(R.id.heightValue)
        btnPlusAge = findViewById(R.id.btnAddAge)
        btnPlusWeight = findViewById(R.id.btnAddWeight)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        txtAge = findViewById(R.id.txtAge)
        txtWeight = findViewById(R.id.txtWeight)
    }

    private fun setActive(isViewSelected:Boolean,ownCard:CardView,disabledCard:CardView) {
        if(!isViewSelected) {
            val selectedColor = ContextCompat.getColor(this,R.color.background_component_selected)
            val unselectedColor = ContextCompat.getColor(this, R.color.background_component)

            isSelected = !isSelected
            ownCard.setCardBackgroundColor(selectedColor)
            disabledCard.setCardBackgroundColor(unselectedColor)
        }
    }
    private fun addValue(value:Int,limit:Int):Int {
        var actualValue:Int = value
        if(value < limit) {
            actualValue++
        }
        return actualValue
    }
    private fun subtractValue(value:Int,limit:Int):Int {
        var actualValue:Int = value
        if(value > limit) {
            actualValue--
        }
        return actualValue
    }
    private fun calculate():Double {
        val format = DecimalFormat("#.##")
        val height:Float = heightValueTxt.text.toString().split(" ")[0].toFloat()
        var imc:Double = weightValue/(Math.pow(height.toDouble()/100, 2.0))

        imc = format.format(imc).toDouble()
        return imc
    }
}