package com.example.ecommerceconcept.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.entities.Basket

class ViewPagerProductAdapter(private var images: List<String>) : RecyclerView.Adapter<ViewPagerProductAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemImage: ImageView = itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerProductAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_details, parent, false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(images[position])
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }

}