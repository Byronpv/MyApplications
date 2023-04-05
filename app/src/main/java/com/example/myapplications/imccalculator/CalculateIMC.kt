package com.example.myapplications.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplications.R
import com.example.myapplications.databinding.ActivityCalculateImcBinding
import com.example.myapplications.imccalculator.IMCActivity.Companion.IMC_RESULT

class CalculateIMC : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateImcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateImcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getResultIMC()
        getResultIMC()?.let { initComponents(it) }
    }

    private fun initComponents(result: Double) {

        when (result) {
            in 0.0..18.4 -> {
                binding.status.text = getString(R.string.status_bajopeso)
                binding.status.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                binding.message.text = getString(R.string.bajo_peso)
            }
            in 18.5..24.9 -> {
                binding.status.text = getString(R.string.status_peso_saludable)
                binding.status.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                binding.message.text = getString(R.string.peso_saludable)
            }
            in 25.0..29.9 -> {
                binding.status.text = getString(R.string.status_sobrepeso)
                binding.status.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                binding.message.text = getString(R.string.sobrepeso)
            }
            in 30.0..90.0 -> {
                binding.status.text = getString(R.string.status_obesidad)
                binding.status.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                binding.message.text = getString(R.string.obesidad)
            }
            else -> {
                binding.message.text = getString(R.string.error)
            }
        }

        binding.buttonRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getResultIMC(): Double? {
        val result = intent.extras?.getDouble(IMC_RESULT) ?: -1.0
        binding.numberResult.text = result.toString()
        return result
    }
}