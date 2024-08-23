package com.example.koala_game

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.koala_game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.easyButton.setOnClickListener {
            val intent = Intent(this, Easy::class.java)
            startActivity(intent)
        }

        binding.mediumButton.setOnClickListener {
            val intent = Intent(this, Medium::class.java)
            startActivity(intent)
        }

        binding.hardButton.setOnClickListener {
            val intent = Intent(this, Hard::class.java)
            startActivity(intent)
        }
    }
}
