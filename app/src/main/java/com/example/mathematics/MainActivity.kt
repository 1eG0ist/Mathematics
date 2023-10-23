package com.example.mathematics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val combinatoricsBtn = findViewById<ImageButton>(R.id.CombinatoricsButton)
        combinatoricsBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, CombinatoricsView::class.java)
            startActivity(intent)
        }

    }
}