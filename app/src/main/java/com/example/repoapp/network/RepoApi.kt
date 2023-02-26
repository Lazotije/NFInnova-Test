package com.example.repoapp.network

import com.example.repoapp.model.UserRepo
import org.koin.core.component.KoinComponent
import retrofit2.Response
import retrofit2.http.GET

class RepoApi : KoinComponent {
    internal interface RepoService {
        @GET("users/oktocat/repos")
        suspend fun getRepos() : Response<List<UserRepo>>
    }
}