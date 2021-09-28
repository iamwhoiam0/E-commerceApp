package com.example.ecommerceconcept.main.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.main.data.entities.BestSeller

class BestSellerAdapter(private var bestSeller: List<BestSeller>): RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemCardView: CardView = itemView.findViewById(R.id.item_cv)
        val itemTitle: TextView = itemView.findViewById(R.id.title_tv)
        val itemImage: ImageView = itemView.findViewById(R.id.product_iv)
        val itemPrice: TextView = itemView.findViewById(R.id.price_tv)
        val itemCurrentPrice: TextView = itemView.findViewById(R.id.current_price_tv)
        val itemFavorite: ImageButton = itemView.findViewById(R.id.add_to_favorite)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_best_seller, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = bestSeller[position].title
        holder.itemPrice.text = bestSeller[position].price_without_discount.toString() + "$"
        holder.itemPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemCurrentPrice.text = bestSeller[position].discount_price.toString() + "$"
        Glide.with(holder.itemView.context)
            .load(bestSeller[position].picture)
            .into(holder.itemImage)
        holder.itemFavorite.isSelected = bestSeller[position].is_favorites

        holder.itemFavorite.setOnClickListener {
            if (holder.itemFavorite.isSelected){
                holder.itemFavorite.isSelected = false
                Toast.makeText(holder.itemView.context, "Модель ${bestSeller[position].title} успешно удалена из избранного", Toast.LENGTH_SHORT).show()
            }else{
                holder.itemFavorite.isSelected = true
                Toast.makeText(holder.itemView.context, "Модель ${bestSeller[position].title} успешно добавлена в избранное", Toast.LENGTH_SHORT).show()
            }
        }

        holder.itemCardView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_main_to_productDetailsFragment)
        )
    }

    override fun getItemCount(): Int {
        return bestSeller.size
    }
}