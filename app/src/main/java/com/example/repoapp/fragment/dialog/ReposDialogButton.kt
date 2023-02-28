package com.example.repoapp.fragment.dialog

data class ReposDialogButton(
    val text: String,
    val onClickListener: ((which: Int) -> Unit)? = null
)