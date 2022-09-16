package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(private val fruitsList: List<Fruit>,private val clickListener:(Fruit)->Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


        const val HASIMAGE = 0 // random unique value
        const val NOIMAGE = 1

    override fun getItemViewType(position: Int): Int {
        return if (fruitsList.get(position).name == "Mango") HASIMAGE else NOIMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASIMAGE) {
            val view =
                LayoutWithImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           LayoutWithImageViewHolder(view)
        } else {
            val view =
                LayoutWithoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LayoutWithoutImageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HASIMAGE){
            (holder as FruitWithImageViewHolder).bind(landmarks[position])
        } else{
            (holder as FruitWithoutImageViewHolder).bind(landmarks[position])
        }
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

}

inner class FruitWithImageViewHolder(private val fruitWithImage: FruitWithImageBinding) :
    RecyclerView.ViewHolder(fruitWithImage.root) {
    fun bind(fruit: Fruit) {
        fruitWithImage.fruitImage.setImageResource(fruit.resource!!)
        fruitWithImage.fruitWithImageTitle.text = fruit.title
    }
}
inner class FruitWithoutImageViewHolder(private val fruitWithoutImage: FruitWithoutImageBinding) :
    RecyclerView.ViewHolder(fruitWithoutImage.root) {
    fun bind(fruit: Fruit) {
        fruitWithoutImage.fruitTitle.text = fruit.title
    }
}
