package com.example.repoapp.repository

import com.example.repoapp.getRetrofitService
import com.example.repoapp.model.UserRepo
import com.example.repoapp.network.RepoApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class RepoRepository : KoinComponent {
    private val repoApi: RepoApi by inject()

    suspend fun getRepo(): Response<List<UserRepo>> {
        return getRetrofitService<RepoApi.RepoService>().getRepos()
    }
}