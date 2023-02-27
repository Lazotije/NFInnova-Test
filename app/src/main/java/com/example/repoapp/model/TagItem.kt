package com.example.repoapp.model

data class TagItem(
    val commit: Commit,
    val name: String,
    val node_id: String
)