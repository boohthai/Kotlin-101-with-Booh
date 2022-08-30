package com.example.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layout.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{(calculateTip())}
    }
    fun calculateTip() {
        val stringOfCost = binding.costOfService.text.toString()
        val cost = stringOfCost.toDoubleOrNull()
        if (cost == null) {
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.2
            else -> 0.15
        }
        var tip = cost*tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}