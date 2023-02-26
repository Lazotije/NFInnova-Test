package com.example.repoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.repoapp.viewModel.RepoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    val viewModel by viewModel<RepoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.getRepos()
    }
}