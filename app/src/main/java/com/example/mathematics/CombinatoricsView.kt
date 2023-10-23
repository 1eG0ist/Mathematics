package com.example.mathematics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class CombinatoricsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combinatorics_view)

        val inputFact = findViewById<EditText>(R.id.factorial_input)
        val resultFactField = findViewById<TextView>(R.id.factorial_result)
        val getResultBtn = findViewById<ImageButton>(R.id.factorial_confirm_input_button)
        getResultBtn.setOnClickListener {
            resultFactField.setText(fact(inputFact.text.toString().toInt()).toString())
        }
    }

    fun fact(n: Int): Long {
        var result: Long = 1
        for (i in 1..n) {
            result *= i
        }
        return result
    }
}