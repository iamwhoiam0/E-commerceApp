package com.example.ecommerceconcept.presentation.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.ecommerceconcept.data.entities.MyCartDataItem
import com.example.ecommerceconcept.data.entities.TestEntitiesItem
import com.example.ecommerceconcept.data.repository.MainRepository
import com.example.ecommerceconcept.utils.NetworkHelper
import com.example.ecommerceconcept.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private val _mainItem = MutableLiveData<Resource<ArrayList<TestEntitiesItem>>>()
    val mainItem: LiveData<Resource<ArrayList<TestEntitiesItem>>>
        get() = _mainItem


    init {
        getMain()
    }

    private fun getMain(){
        viewModelScope.launch {
            _mainItem.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                mainRepository.getMain().let {
                    if (it.isSuccessful){
                        _mainItem.postValue(Resource.success(it.body()))
                    } else{
                        _mainItem.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            }else {
                _mainItem.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}