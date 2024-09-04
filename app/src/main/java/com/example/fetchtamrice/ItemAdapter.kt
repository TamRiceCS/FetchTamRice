package com.example.fetchtamrice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private var iList: ArrayList<ItemEntity>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates each individual task card
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // used to bind info to each task
        var IDVal = iList.get(position).itemID
        var nameVal = iList.get(position).name

        holder.ID.text = IDVal.toString()
        holder.itemName.text = nameVal
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return iList.size
    }

    fun alertChange(newList: ArrayList<ItemEntity>) {
        iList = newList
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ID: TextView = itemView.findViewById(R.id.cardID)
        val itemName: TextView = itemView.findViewById(R.id.cardItem)

    }
}
