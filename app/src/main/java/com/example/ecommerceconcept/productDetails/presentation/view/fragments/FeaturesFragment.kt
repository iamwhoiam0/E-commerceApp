package com.example.ecommerceconcept.productDetails.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.productDetails.data.entities.ProductDetailsDataItem

class FeaturesFragment(val productDetailsDataItem: ProductDetailsDataItem) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_features, container, false)
    }

}