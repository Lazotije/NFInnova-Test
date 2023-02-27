package com.example.repoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoapp.model.RepoDetails
import com.example.repoapp.model.TagItem
import com.example.repoapp.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RepoDetailsViewModel : KoinComponent, ViewModel() {
    private val repository by inject<RepoRepository>()

    private val _repoDetails = MutableLiveData<RepoDetails>()
    val repoDetails: LiveData<RepoDetails>
        get() = _repoDetails

    private val _tags = MutableLiveData<List<TagItem>>()
    val tags: LiveData<List<TagItem>>
        get() = _tags

    fun getDetails(repo : String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getDetails(repo)
            if (result.isSuccessful) {
                _repoDetails.postValue(result.body())
            } else {
                //todo
            }
        }
    }

    fun getTags(repo : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getTags(repo)
            if (result.isSuccessful) {
                _tags.postValue(result.body())
            } else {
                //todo
            }
        }
    }
}