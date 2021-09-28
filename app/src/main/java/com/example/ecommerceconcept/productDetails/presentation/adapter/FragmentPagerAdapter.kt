package com.example.ecommerceconcept.productDetails.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceconcept.productDetails.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.productDetails.presentation.view.fragments.DetailsFragment
import com.example.ecommerceconcept.productDetails.presentation.view.fragments.FeaturesFragment
import com.example.ecommerceconcept.productDetails.presentation.view.fragments.ShopFragment

class FragmentPagerAdapter(var productDetailsDataItem: ProductDetailsDataItem, private var fragmentManager: FragmentManager, var lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

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