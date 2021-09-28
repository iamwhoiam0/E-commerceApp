package com.example.ecommerceconcept.main.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceconcept.main.data.entities.MainDataItem
import com.example.ecommerceconcept.main.data.repository.MainRepository
import com.example.ecommerceconcept.utils.NetworkHelper
import com.example.ecommerceconcept.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _mainItem = MutableLiveData<Resource<ArrayList<MainDataItem>>>()
    val mainItem: LiveData<Resource<ArrayList<MainDataItem>>>
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