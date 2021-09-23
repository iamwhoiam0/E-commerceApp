package com.example.ecommerceconcept.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.presentation.view.fragments.DetailsFragment
import com.example.ecommerceconcept.presentation.view.fragments.FeaturesFragment
import com.example.ecommerceconcept.presentation.view.fragments.ShopFragment

class FragmentPagerAdapter(var productDetailsDataItem: ProductDetailsDataItem, var fragmentManager: FragmentManager, var lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return ShopFragment(productDetailsDataItem)
            }
            1 ->{
                return DetailsFragment(productDetailsDataItem)
            }
            2 ->{
                return FeaturesFragment(productDetailsDataItem)
            }
        }

        return ShopFragment(productDetailsDataItem)
    }

}