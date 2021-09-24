package com.example.ecommerceconcept.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceconcept.R

class MenuAdapter(private var menuItems: List<String>, private var drawableItems: List<Int>, private var flag: String) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemImage: ImageView = itemView.findViewById(R.id.image_item)
        val itemText: TextView = itemView.findViewById(R.id.text_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemImage.setImageResource(drawableItems[position])
        holder.itemText.text = menuItems[position]

        if (flag[position] == '1'){
            holder.itemImage.isSelected = true
            holder.itemImage.setColorFilter(Color.WHITE)
            holder.itemText.isSelected = true
        }else{
            holder.itemImage.isSelected = false
            holder.itemImage.colorFilter = null
            holder.itemText.isSelected = false
        }
        holder.itemImage.setOnClickListener {
            flag = flag.replace('1', '0')
            flag = flag.substring(0,position) + '1' + flag.substring(position, 5)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

}