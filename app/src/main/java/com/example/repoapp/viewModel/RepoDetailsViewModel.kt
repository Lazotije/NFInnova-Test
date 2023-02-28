package com.example.repoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoapp.R
import com.example.repoapp.model.RepoDetails
import com.example.repoapp.model.TagItem
import com.example.repoapp.repository.RepoRepository
import com.example.repoapp.utils.Strings
import com.example.repoapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RepoDetailsViewModel : KoinComponent, ViewModel() {
    private val repository by inject<RepoRepository>()

    private val _repoDetails = MutableLiveData<RepoDetails>()
    val repoDetails: LiveData<RepoDetails>
        get() = _repoDetails

    private val _reposDetailsResponse = MutableLiveData<Resource<Unit>>()
    val repoDetailsResponse : LiveData<Resource<Unit>>
        get() = _reposDetailsResponse

    private val _tags = MutableLiveData<List<TagItem>>()
    val tags: LiveData<List<TagItem>>
        get() = _tags

    fun getDetails(repo : String){
        _reposDetailsResponse.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getDetails(repo)
            if (result.isSuccessful) {
                _repoDetails.postValue(result.body())
                _reposDetailsResponse.postValue(Resource.success())
            } else {
                _reposDetailsResponse.postValue(Resource.error(Strings.get(R.string.error_generic)))
            }
        }
    }

    fun getTags(repo : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getTags(repo)
            if (result.isSuccessful) {
                _tags.postValue(result.body())
            } else {
                _reposDetailsResponse.postValue(Resource.error(Strings.get(R.string.error_generic)))
            }
        }
    }

    fun resetState() {
        _reposDetailsResponse.value = Resource.loading()
    }
}