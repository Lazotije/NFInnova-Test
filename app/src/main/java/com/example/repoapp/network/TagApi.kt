package com.example.repoapp.network

import com.example.repoapp.model.TagItem
import org.koin.core.component.KoinComponent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class TagApi : KoinComponent {
    internal interface TagService {
        @GET("repos/oktocat/{repo}/tags")
        suspend fun getTags(@Path("repo") repo : String): Response<List<TagItem>>
    }
}