package com.example.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private val exchangeRates = mapOf(
        "USD-EUR" to 0.92, "EUR-USD" to 1.09,
        "USD-GBP" to 0.77, "GBP-USD" to 1.30,
        "USD-JPY" to 152.93, "JPY-USD" to 0.0065,
        "EUR-GBP" to 0.83, "GBP-EUR" to 1.20,
        "EUR-JPY" to 165.74, "JPY-EUR" to 0.0060,
        "GBP-JPY" to 198.56, "JPY-GBP" to 0.0050,
        "USD-VND" to 25294.94, "VND-USD" to 0.000041,
        "EUR-VND" to 27333.0, "VND-EUR" to 0.000035
    )

    private val currencyCodes = mapOf(
        "United States - Dollar" to "USD",
        "Europe - Euro" to "EUR",
        "United Kingdom - Pound" to "GBP",
        "Japan - Yen" to "JPY",
        "Viet Nam - Dong" to "VND"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)
        val currencies = currencyCodes.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        val textViewSymbolFrom: TextView = findViewById(R.id.textViewSymbolFrom)
        val textViewSymbolTo: TextView = findViewById(R.id.textViewSymbolTo)
        val editTextFrom: EditText = findViewById(R.id.editTextFrom)
        val editTextTo: EditText = findViewById(R.id.editTextTo)
        val textViewExchangeRate: TextView = findViewById(R.id.textViewExchangeRate)

        var isFromFieldActive = true

        val updateRateAndConversion = {
            updateExchangeRate(spinnerFrom, spinnerTo, textViewExchangeRate, isFromFieldActive)
            convertCurrency(spinnerFrom, spinnerTo, editTextFrom, editTextTo, isFromFieldActive)
        }

        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                textViewSymbolFrom.text = getSymbolForCurrency(currencies[position])
                updateRateAndConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                textViewSymbolTo.text = getSymbolForCurrency(currencies[position])
                updateRateAndConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        editTextFrom.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                isFromFieldActive = true
                updateExchangeRate(spinnerFrom, spinnerTo, textViewExchangeRate, true)
            }
        }

        editTextTo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                isFromFieldActive = false
                updateExchangeRate(spinnerFrom, spinnerTo, textViewExchangeRate, false)
            }
        }

        editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isFromFieldActive) updateRateAndConversion()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        editTextTo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!isFromFieldActive) updateRateAndConversion()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun updateExchangeRate(
        spinnerFrom: Spinner,
        spinnerTo: Spinner,
        textViewExchangeRate: TextView,
        isFromFieldActive: Boolean
    ) {
        val currencyFrom = currencyCodes[spinnerFrom.selectedItem.toString()] ?: return
        val currencyTo = currencyCodes[spinnerTo.selectedItem.toString()] ?: return
        val rateKey = if (isFromFieldActive) "$currencyFrom-$currencyTo" else "$currencyTo-$currencyFrom"
        val exchangeRate = exchangeRates[rateKey] ?: 1.0

        textViewExchangeRate.text = "1 ${if (isFromFieldActive) currencyFrom else currencyTo} = $exchangeRate ${if (isFromFieldActive) currencyTo else currencyFrom}"
    }

    private fun convertCurrency(
        spinnerFrom: Spinner,
        spinnerTo: Spinner,
        editTextFrom: EditText,
        editTextTo: EditText,
        isFromFieldActive: Boolean
    ) {
        val currencyFrom = currencyCodes[spinnerFrom.selectedItem.toString()] ?: return
        val currencyTo = currencyCodes[spinnerTo.selectedItem.toString()] ?: return
        val rateKey = if (isFromFieldActive) "$currencyFrom-$currencyTo" else "$currencyTo-$currencyFrom"
        val exchangeRate = exchangeRates[rateKey] ?: 1.0

        if (isFromFieldActive) {
            val amountFrom = editTextFrom.text.toString().toDoubleOrNull() ?: return
            editTextTo.setText(String.format("%.2f", amountFrom * exchangeRate))
        } else {
            val amountTo = editTextTo.text.toString().toDoubleOrNull() ?: return
            editTextFrom.setText(String.format("%.2f", amountTo * exchangeRate))
        }
    }

    private fun getSymbolForCurrency(currency: String): String {
        return when (currency) {
            "United States - Dollar" -> "$"
            "Europe - Euro" -> "€"
            "United Kingdom - Pound" -> "£"
            "Japan - Yen" -> "¥"
            "Viet Nam - Dong" -> "₫"
            else -> ""
        }
    }
}
