package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtWeight = findViewById<EditText>(R.id.edtWeight)
        val edtHeightFt = findViewById<EditText>(R.id.edtHeightFt)
        val edtHeightIn = findViewById<EditText>(R.id.edtHeightIn)
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val output = findViewById<TextView>(R.id.tvOutput)

        fun bmiCalculate(): Double {
            try {
                val kgs = edtWeight.text.toString().toInt()
                val ft = edtHeightFt.text.toString().toInt()
                val inch = edtHeightIn.text.toString().toInt()

                val totalIn = (ft*12)+inch
                val totalCm = totalIn*2.53
                val totalM = totalCm/100
                return kgs/(totalM*totalM)
            } catch(e: Exception){
                Toast.makeText(this, "Please Enter Valid Inputs", Toast.LENGTH_SHORT).show()
            }
            return -1.0
        }

        btnCalc.setOnClickListener {
            val bmi = bmiCalculate()
            if(bmi>25) {
                output.text = getString(R.string.you_are_overweight)
                output.setBackgroundColor(getColor(R.color.red))
            } else if(bmi<18 && bmi>0){
                output.text = getString(R.string.you_are_underweight)
                output.setBackgroundColor(getColor(R.color.yellow))
            }
            else if(bmi>18 && bmi<25){
                output.text = getString(R.string.you_are_healthy)
                output.setBackgroundColor(getColor(R.color.green))
            }

        }
    }
}