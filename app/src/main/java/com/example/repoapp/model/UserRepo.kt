package com.example.repoapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepo(
    val has_issues: Boolean,
    val id: Int,
    val name: String,
    val open_issues: Int,
    val open_issues_count: Int,
    val owner: Owner
) : Parcelable