package com.example.ecommerceconcept.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.entities.Basket

class MyCartAdapter(private var myBasket: List<Basket>): RecyclerView.Adapter<MyCartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.title_cart_tv)
        val itemImage: ImageView = itemView.findViewById(R.id.image_cart_iv)
        val itemPrice: TextView = itemView.findViewById(R.id.price_cart_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyCartAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = myBasket[position].title
        holder.itemPrice.text = "$" + myBasket[position].price + ".00"  // при float данных: String.format("%.2f", myBasket[position].price)
        Glide.with(holder.itemView.context)
            .load(myBasket[position].image)
            .into(holder.itemImage)
        holder.itemImage.clipToOutline = true
    }

    override fun getItemCount(): Int {
        return myBasket.size
    }
}