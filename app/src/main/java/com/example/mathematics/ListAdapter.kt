package com.example.mathematics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class ListAdapter(context: Context, dataArrayList: ArrayList<ListData?>?):
ArrayAdapter<ListData?>(context, R.layout.list_item, dataArrayList!!){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        var listData = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val numberOfPermutations = view!!.findViewById<TextView>(R.id.numberOfPermutations)
        val permutationText = view.findViewById<TextView>(R.id.permutationText)

        numberOfPermutations.text = listData!!.num
        permutationText.text = listData.perm

        return view
    }

}