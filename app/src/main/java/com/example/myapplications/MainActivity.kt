package com.example.myapplications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplications.databinding.ActivityMainBinding
import com.example.myapplications.imccalculator.IMCActivity

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imcButton.setOnClickListener {
            navigateImcCalculator()
        }
    }

    private fun navigateImcCalculator() {
        val intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }
}