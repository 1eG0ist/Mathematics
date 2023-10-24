package com.example.mathematics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ArrayAdapter
import com.example.mathematics.databinding.ActivityCombinatoricsViewBinding
import java.util.LinkedList

class CombinatoricsView : AppCompatActivity() {
    private lateinit var binding: ActivityCombinatoricsViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // connecting to the desired layout
        super.onCreate(savedInstanceState)
        binding = ActivityCombinatoricsViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // work with layout features

        binding.factorialConfirmInputButton.setOnClickListener {
            binding.factorialResult.text = fact(binding.factorialInput.text.toString().toInt()).toString()
        }

        binding.combinationsConfirmInputButton.setOnClickListener {
            if (binding.inputElements.text != null && binding.inputElements.text.toString() != "") {

                //TODO create own adapter

                if (binding.countOfInputElements.text != null && binding.countOfInputElements.text.toString() != "") {

                    binding.listWithRepeats.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                        GeneratePermutations(binding.countOfInputElements.text.toString().toInt(),
                        binding.inputElements.text.toString().toCharArray()).permutations)

                    binding.listWithOutRepeats.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                        GeneratePermutations(binding.countOfInputElements.text.toString().toInt(),
                        binding.inputElements.text.toString().toCharArray()).permutations.toSet().toList())

                } else {

                    binding.listWithRepeats.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                        GeneratePermutations(binding.inputElements.text.toString().length,
                        binding.inputElements.text.toString().toCharArray()).permutations)

                    binding.listWithOutRepeats.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                        GeneratePermutations(binding.inputElements.text.toString().length,
                            binding.inputElements.text.toString().toCharArray()).permutations.toSet().toList())

                }
            } else {
                // TODO create toast with hint that we need symbols from user
            }
        }

    }

    private fun fact(n: Int): Long {
        var result: Long = 1
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    class CollectPermutations {
        var permutations: ArrayList<String> = ArrayList()
    }

    fun GeneratePermutations(length: Int, symbols: CharArray): CollectPermutations {
        var permutations = CollectPermutations()
        var currentPermutation = CharArray(length)
        GeneratePermutationsRecursively(permutations, symbols, currentPermutation, length, 0)
        return permutations
    }

    fun GeneratePermutationsRecursively(collPerms: CollectPermutations, operators: CharArray,
                                        currentPermutations: CharArray, length: Int, index: Int) {
        if (index == length)
        {
            collPerms.permutations.add(String(currentPermutations))
            return
        }
        for (op: Char in operators)
        {
            currentPermutations.set(index, op)
            GeneratePermutationsRecursively(collPerms, operators, currentPermutations, length, index + 1)
        }
    }
}