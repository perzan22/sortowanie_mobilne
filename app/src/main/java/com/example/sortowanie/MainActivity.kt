package com.example.sortowanie

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sortowanie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun sort(view: View) {

        if (binding.numbersToSort.text.isNotEmpty()) {

            val input = binding.numbersToSort.text.toString()
            val numbers = IntArray(input.length)

            for (i in input.indices) {
                numbers[i] = input[i].digitToInt()
            }

            //val numbers = input.split("").map { it.toInt() }.toTypedArray() // tablica liczb

            quickSort(numbers, 0, numbers.size - 1)

            //binding.textView.text = numbers.joinToString("")
            binding.textView.text = numbers.joinToString("")
        } else {
            println("Funkcja dziala")
            binding.textView.text = getString(R.string.no_value_string)
        }
    }

    private fun quickSort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {

            val pivot: Int = partitionFunction(arr, low, high)

            quickSort(arr, low, pivot - 1)

            quickSort(arr, pivot + 1, high)
        }
    }

    private fun partitionFunction(arr: IntArray, low: Int, high: Int): Int {

        var i: Int = low
        val pivot: Int = arr[high]

        for (k in low..high) {
            if (arr[k] < pivot) {
                arr.swap(i, k)
                i++
            }
        }
        arr.swap(i, high)
        return i
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

