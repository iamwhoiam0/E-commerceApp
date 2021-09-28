package com.example.ecommerceconcept.cart.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.cart.data.entities.Basket

class MyCartAdapter(private var myBasket: ArrayList<Basket>): RecyclerView.Adapter<MyCartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.title_cart_tv)
        val itemImage: ImageView = itemView.findViewById(R.id.image_cart_iv)
        val itemPrice: TextView = itemView.findViewById(R.id.price_cart_tv)

        val increase: TextView = itemView.findViewById(R.id.increase_tv)
        val decrease: TextView = itemView.findViewById(R.id.decrease_tv)
        val counter: TextView = itemView.findViewById(R.id.counter_tv)

        val deleteBtn: ImageButton = itemView.findViewById(R.id.delete_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = stringConverter(myBasket[position].title)
        holder.itemPrice.text = "$" + myBasket[position].price + ".00"  // при float данных: String.format("%.2f", myBasket[position].price)
        holder.counter.text = "1"
        Glide.with(holder.itemView.context)
            .load(myBasket[position].image)
            .into(holder.itemImage)
        holder.itemImage.clipToOutline = true

        holder.increase.setOnClickListener {
            val newCount = Integer.parseInt(holder.counter.text.toString()) + 1
            if (newCount > 0){
                holder.counter.text = newCount.toString()
                holder.itemPrice.text = "$" + myBasket[position].price * newCount + ".00"
            }
        }

        holder.decrease.setOnClickListener {
            val newCount = Integer.parseInt(holder.counter.text.toString()) - 1
            if (newCount > 0) {
                holder.counter.text =
                    (Integer.parseInt(holder.counter.text.toString()) - 1).toString()
                holder.itemPrice.text = "$" + myBasket[position].price * newCount + ".00"
            }
        }

        holder.deleteBtn.setOnClickListener {
            myBasket.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
    }

    private fun stringConverter(title: String):String {
        var newStr = ""
        val strArr = title.split(" ")
        if (strArr[0].length + strArr[1].length > 14){
            newStr = strArr[0] + "\n" + strArr.reduceIndexed { index, acc, s ->
                if (index > 0){
                    return@reduceIndexed "$acc $s"
                }
                return@reduceIndexed ""
            }
        } else{
            newStr = strArr[0] + " " + strArr[1] + "\n" + strArr.reduceIndexed { index, acc, s ->
                if (index > 1){
                    return@reduceIndexed "$acc $s"
                }
                return@reduceIndexed ""
            }
        }
        return newStr
    }

    override fun getItemCount(): Int {
        return myBasket.size
    }

}