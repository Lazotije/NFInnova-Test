package com.example.repoapp.repository

import com.example.repoapp.model.RepoDetails
import com.example.repoapp.model.TagItem
import com.example.repoapp.model.UserRepo
import com.example.repoapp.network.OwnerApi
import com.example.repoapp.network.RepoApi
import com.example.repoapp.network.TagApi
import com.example.repoapp.utils.getRetrofitService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class RepoRepository : KoinComponent {
    suspend fun getRepo(): Response<List<UserRepo>> {
        return getRetrofitService<RepoApi.RepoService>().getRepos()
    }

    suspend fun getDetails(repo : String): Response<RepoDetails> {
        return getRetrofitService<OwnerApi.OwnerService>().getDetails(repo)
    }

    suspend fun getTags(repo : String): Response<List<TagItem>> {
        return getRetrofitService<TagApi.TagService>().getTags(repo)
    }
}