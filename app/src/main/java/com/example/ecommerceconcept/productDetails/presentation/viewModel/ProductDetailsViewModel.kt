package com.example.ecommerceconcept.productDetails.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceconcept.productDetails.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.productDetails.data.repository.ProductDetailsRepository
import com.example.ecommerceconcept.utils.NetworkHelper
import com.example.ecommerceconcept.utils.Resource
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productDetailsRepository: ProductDetailsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _productDetails = MutableLiveData<Resource<ArrayList<ProductDetailsDataItem>>>()
    val productDetails: LiveData<Resource<ArrayList<ProductDetailsDataItem>>>
        get() = _productDetails

    init {
        getProductDetails()
    }

    private fun getProductDetails(){
        viewModelScope.launch {
            _productDetails.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                productDetailsRepository.getProductDetails().let {
                    if (it.isSuccessful){
                        _productDetails.postValue(Resource.success(it.body()))
                    } else{
                        _productDetails.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _productDetails.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}