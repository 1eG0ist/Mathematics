package com.example.mathematics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.mathematics.databinding.ActivityCombinatoricsViewBinding
import java.util.LinkedList

class CombinatoricsView : AppCompatActivity() {
    private lateinit var binding: ActivityCombinatoricsViewBinding
    private lateinit var listAdapterWithRepeats: ListAdapter
    private lateinit var listAdapterWithOutRepeats: ListAdapter
    private lateinit var listData: ListData
    var dataArrayListWithRepeats = ArrayList<ListData?>()
    var dataArrayListWithOutRepeats = ArrayList<ListData?>()
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
                val permutationsList: ArrayList<String>

                if (binding.countOfInputElements.text != null && binding.countOfInputElements.text.toString() != "") {

                    permutationsList = GeneratePermutations(
                        binding.countOfInputElements.text.toString().toInt(),
                        binding.inputElements.text.toString().toCharArray()
                    ).permutations

                } else {

                    permutationsList = GeneratePermutations(
                        binding.inputElements.text.toString().length,
                        binding.inputElements.text.toString().toCharArray()
                    ).permutations

                }

                val permutationsWithOutRepeats: List<String> = permutationsList.toSet().toList()
                dataArrayListWithRepeats.clear()
                dataArrayListWithOutRepeats.clear()

                if (permutationsList.size > 0) {
                    binding.lineBetweenLists.visibility = View.VISIBLE
                } else {
                    binding.lineBetweenLists.visibility = View.INVISIBLE
                }

                // working with adapter for list with repeats

                for (i in permutationsList.indices) {
                    listData = ListData("${i+1}.", permutationsList[i])
                    dataArrayListWithRepeats.add(listData)
                }

                listAdapterWithRepeats = ListAdapter(this, dataArrayListWithRepeats)
                binding.listWithRepeats.adapter = listAdapterWithRepeats

                // working with adapter for list without repeats

                for (i in permutationsWithOutRepeats.indices) {
                    listData = ListData("${i+1}.", permutationsWithOutRepeats[i])
                    dataArrayListWithOutRepeats.add(listData)
                }

                listAdapterWithOutRepeats = ListAdapter(this, dataArrayListWithOutRepeats)
                binding.listWithOutRepeats.adapter = listAdapterWithOutRepeats

            } else {
                dataArrayListWithRepeats.clear()
                dataArrayListWithOutRepeats.clear()
                listAdapterWithRepeats = ListAdapter(this, dataArrayListWithRepeats)
                binding.listWithRepeats.adapter = listAdapterWithRepeats
                listAdapterWithOutRepeats = ListAdapter(this, dataArrayListWithOutRepeats)
                binding.listWithOutRepeats.adapter = listAdapterWithOutRepeats
                binding.lineBetweenLists.visibility = View.INVISIBLE
                Toast.makeText(this, "Требуется ввести хотя бы 1 символ", Toast.LENGTH_SHORT).show()
            }
        }

        binding.placementConfirmInputButton.setOnClickListener {
            if (binding.placementBottomInput.text != null && binding.placementBottomInput.text.toString() != "" &&
                    binding.placementTopInput.text != null && binding.placementTopInput.text.toString() != "") {

                binding.placementResult.text = (
                        (fact(binding.placementBottomInput.text.toString().toInt()))/
                                (fact(binding.placementBottomInput.text.toString().toInt() -
                                        binding.placementTopInput.text.toString().toInt()))
                        ).toString()

            } else {
                Toast.makeText(this, "Недостаточно данных", Toast.LENGTH_SHORT).show()
                binding.placementResult.text = null
            }

        }

        binding.combinationConfirmInputButton.setOnClickListener {
            if (binding.combinationBottomInput.text != null && binding.combinationBottomInput.text.toString() != "" &&
                binding.combinationTopInput.text != null && binding.combinationTopInput.text.toString() != "") {

                binding.combinationResult.text = (
                        (fact(binding.combinationBottomInput.text.toString().toInt()))/
                                (fact(binding.combinationBottomInput.text.toString().toInt() -
                                        binding.combinationTopInput.text.toString().toInt()) *
                                fact(binding.combinationTopInput.text.toString().toInt()))
                        ).toString()

            } else {
                Toast.makeText(this, "Недостаточно данных", Toast.LENGTH_SHORT).show()
                binding.combinationResult.text = null
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