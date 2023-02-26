package com.example.repoapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoapp.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RepoViewModel : KoinComponent, ViewModel() {
    val repository by inject<RepoRepository>()

    fun getRepos(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRepo()
            if (result.isSuccessful) {
                Log.d("LAZA", "RESULT  " + result.body())
            } else {
                Log.d("LAZA", "VAJDU GA");
            }
        }
    }

}