package com.example.repoapp.model

import com.google.gson.annotations.SerializedName

data class RepoDetails(
    val forks: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    val watchers: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
)