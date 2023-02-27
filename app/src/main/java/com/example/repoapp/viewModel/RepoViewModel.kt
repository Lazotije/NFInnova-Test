package com.example.repoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoapp.model.UserRepo
import com.example.repoapp.repository.RepoRepository
import com.example.repoapp.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RepoViewModel : KoinComponent, ViewModel() {
    private val repository by inject<RepoRepository>()

    private val _repos = MutableLiveData<MutableList<UserRepo>>()
    val repos: LiveData<MutableList<UserRepo>>
        get() = _repos

    private val _repoSelected = SingleLiveEvent<UserRepo>()
    val repoSelected: LiveData<UserRepo>
        get() = _repoSelected


    fun getRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRepo()
            if (result.isSuccessful) {
                _repos.postValue(result.body()?.toMutableList())
            } else {
                _repos.postValue(mutableListOf())
            }
        }
    }

    fun repoClicked(position: Int) {
        val post = _repos.value?.get(position)
        post?.let {
            _repoSelected.postValue(it)
        }
    }
}