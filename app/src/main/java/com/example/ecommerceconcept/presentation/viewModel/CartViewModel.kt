package com.example.ecommerceconcept.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceconcept.data.entities.MyCartDataItem
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.data.repository.MainRepository
import com.example.ecommerceconcept.utils.Resource
import kotlinx.coroutines.launch

class CartViewModel(
    private val mainRepository: MainRepository,
    //private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _cartItem = MutableLiveData<Resource<ArrayList<MyCartDataItem>>>()
    val cartItem: LiveData<Resource<ArrayList<MyCartDataItem>>>
        get() = _cartItem

    init {
        getCart()
    }

    private fun getCart(){
        viewModelScope.launch {
            _cartItem.postValue(Resource.loading(null))
            //if (networkHelper.isNetworkConnected()){
            mainRepository.getCart().let {
                if (it.isSuccessful){
                    _cartItem.postValue(Resource.success(it.body()))
                } else{
                    _cartItem.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
//            }else {
//                _cartItem.postValue(Resource.error("No internet connection", null))
//            }
        }
    }

}