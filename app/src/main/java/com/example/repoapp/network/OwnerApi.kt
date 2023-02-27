package com.example.repoapp.network

import com.example.repoapp.model.RepoDetails
import org.koin.core.component.KoinComponent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class OwnerApi : KoinComponent{
    internal interface OwnerService {
        @GET("repos/oktocat/{repo}")
        suspend fun getDetails(@Path("repo") repo : String) : Response<RepoDetails>
    }
}