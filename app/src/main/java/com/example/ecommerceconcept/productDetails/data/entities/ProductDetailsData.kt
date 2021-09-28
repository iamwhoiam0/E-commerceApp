package com.example.ecommerceconcept.productDetails.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ProductDetailsData : ArrayList<ProductDetailsDataItem>()

@Parcelize
data class ProductDetailsDataItem(
    val CPU: String,
    val _id: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val images: List<String>,
    val is_favorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
): Parcelable