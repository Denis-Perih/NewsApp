package com.startandroid.newsapp.ui.mostpopular.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.repository.repositorymostpopular.MostPopularRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.launch

class MostPopularViewModel(private val repository: MostPopularRepository) : ViewModel() {

    private val mostPopularLiveData = MutableLiveData<Result<PopularNews>>()

    init {
        viewModelScope.launch {
            fetchMostPopular()
        }
    }

    private suspend fun fetchMostPopular() {
        try {
            val mostPopularData = repository.getMostPopular()
            mostPopularLiveData.postValue(Result.successData(mostPopularData))
        }catch (ex:NetworkErrorException){
            mostPopularLiveData.postValue(Result.errorData(null))
        }
    }

    fun getMostPopular(): LiveData<Result<PopularNews>> {
        return mostPopularLiveData
    }
}