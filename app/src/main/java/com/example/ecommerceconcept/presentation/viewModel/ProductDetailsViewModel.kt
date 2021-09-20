package com.example.ecommerceconcept.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.ecommerceconcept.data.api.ApiHelper
import com.example.ecommerceconcept.data.repository.MainRepository
import com.example.ecommerceconcept.utils.Resource
import kotlinx.coroutines.Dispatchers

class ProductDetailsViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun getProductDetails() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getProductDetails()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}

class ProductDetailsViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            return ProductDetailsViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}