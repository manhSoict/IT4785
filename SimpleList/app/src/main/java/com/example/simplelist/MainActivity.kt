package com.example.simplelist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var etNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNumber = findViewById(R.id.etNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            tvError.visibility = TextView.GONE
            val nStr = etNumber.text.toString()
            if (nStr.isEmpty() || nStr.toIntOrNull() == null || nStr.toInt() <= 0) {
                tvError.visibility = TextView.VISIBLE
                tvError.text = "Dữ liệu nhập vào không hợp lệ!"
            } else {
                val n = nStr.toInt()
                val resultList = when {
                    rbEven.isChecked -> getEvenNumbers(n)
                    rbOdd.isChecked -> getOddNumbers(n)
                    rbSquare.isChecked -> getSquareNumbers(n)
                    else -> emptyList()
                }
                listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { isSquare(it) }
    }

    private fun isSquare(num: Int): Boolean {
        val root = Math.sqrt(num.toDouble()).toInt()
        return root * root == num
    }
}