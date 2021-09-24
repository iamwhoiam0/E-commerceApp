package com.example.ecommerceconcept.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView


class HorizontalSpacingItemDecoration(private var space: Int): RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {


        // Add top margin only for the first item to avoid double space between items
        when(parent.getChildAdapterPosition(view)){
            0 ->{
                outRect.left = space * 4
                outRect.right = space
            }
            4 -> {
                outRect.left = space
                outRect.right = space * 4
            }
            else ->{
                outRect.left = space
                outRect.right = space
            }
        }
    }

}