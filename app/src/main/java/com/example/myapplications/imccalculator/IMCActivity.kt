package com.example.myapplications.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplications.R
import com.example.myapplications.databinding.ActivityImcactivityBinding

class IMCActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcactivityBinding
    private var isMaleSelected = true
    private var currentWeight = 60
    private var currentAge = 25
    private var currentHeight = 120

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initUI()
    }

    private fun initListeners() {
        with(binding) {
            viewMale.setOnClickListener {
                isMaleSelected = true
                setGenderColor()
            }

            viewFemale.setOnClickListener {
                isMaleSelected = false
                setGenderColor()
            }

            rangeSliderHeight.addOnChangeListener { _, value, _ ->
                currentHeight = value.toInt()
                slideTextNumber.text =
                    getString(R.string.height_centimeters, currentHeight.toString())
            }

            btnPlusWeight.setOnClickListener {
                currentWeight += 1
                setWeight()
            }
            btnSubtractWeight.setOnClickListener {
                currentWeight -= 1
                setWeight()
            }

            btnPlusAge.setOnClickListener {
                currentAge += 1
                setAge()
            }
            btnSubtractAge.setOnClickListener {
                currentAge -= 1
                setAge()
            }

            btnCalculate.setOnClickListener {
                navigateCalculateIMC(calculateIMC())
            }
        }
    }

    private fun setWeight() {
        binding.textViewWeight.text = currentWeight.toString()
    }

    private fun setHeight() {
        binding.slideTextNumber.text = currentHeight.toString()
    }

    private fun setAge() {
        binding.textViewAge.text = currentAge.toString()
    }

    private fun setGenderColor() {
        with(binding) {
            viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
            viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
        }
    }

    private fun getBackgroundColor(isMaleSelected: Boolean): Int {
        val colorReference = if (isMaleSelected) {
            R.color.background_component
        } else {
            R.color.background_component_selected
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setHeight()
        setWeight()
        setAge()
    }

    private fun calculateIMC(): Double {
        val result = (currentWeight / ((currentHeight * currentHeight) / 100).toDouble()) * 100
        return String.format("%.2f", result).toDouble()
    }

    private fun navigateCalculateIMC(imcResult: Double) {
        val intent = Intent(this, CalculateIMC::class.java)
        intent.putExtra(IMC_RESULT, imcResult)
        startActivity(intent)
    }

    companion object {
        const val IMC_RESULT = "IMC_RESULT"
    }

}