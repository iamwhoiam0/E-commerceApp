package com.example.ecommerceconcept.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.entities.HomeStore

class ViewPagerAdapter(private var homeStore: List<HomeStore>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.title_tv)
        val itemSubtitle: TextView = itemView.findViewById(R.id.subtitle_tv)
        val itemImage: ImageView = itemView.findViewById(R.id.background_iv)
        val itemNew: ImageView = itemView.findViewById(R.id.new_iv)
        val itemNewTv: TextView = itemView.findViewById(R.id.new_tv)

        init {
            itemImage.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Вы нажали на ${position + 1} позицию", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hot_sales, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = homeStore[position].title
        holder.itemSubtitle.text = homeStore[position].subtitle
        Glide.with(holder.itemView.context)
            .load(homeStore[position].picture)
            .into(holder.itemImage)

        if (homeStore[position].is_new){
            holder.itemNew.visibility = View.VISIBLE
            holder.itemNewTv.visibility = View.VISIBLE
        }else{
            holder.itemNew.visibility = View.INVISIBLE
            holder.itemNewTv.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return homeStore.size
    }

}