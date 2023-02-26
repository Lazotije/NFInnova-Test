package com.example.repoapp.di

import com.example.repoapp.network.RepoApi
import com.example.repoapp.repository.RepoRepository
import com.example.repoapp.viewModel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //API
    single { RepoApi() }

    //repository
    single { RepoRepository() }

    //view model
    viewModel{RepoViewModel()}
}