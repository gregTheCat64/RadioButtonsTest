package com.example.radiobuttonstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.example.radiobuttonstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE)
        val editor = prefs.edit()
        if (prefs.contains(PREFS_FILE)) {
            value = prefs.getInt(PREFS_FILE, 0)
            binding.savedValue.setText(value.toString())
        }

        binding.x2multiplyer.isChecked = true

        binding.saveButton.setOnClickListener {
            if (binding.editTextNumber.text.toString().isNotEmpty()) {
                value = binding.editTextNumber.text.toString().toInt()
                binding.savedValue.setText(value.toString())
                editor.putInt(PREFS_FILE, value)
                editor.apply()
            }
        }

        binding.calcBtn.setOnClickListener {
            calculate()
            binding.result.setText(result.toString())
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                multipliyer = when (checkedId) {
                    R.id.x2multiplyer -> 2
                    else -> 4
                }
            }
        }
    }

    companion object {
        val PREFS_FILE = "prefsFile"
        var value = 1
        var multipliyer = 1
        var result = 0
    }

    fun calculate() {
        result = value * multipliyer
    }
}