package com.example.repoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repoapp.BaseFragment
import com.example.repoapp.R
import com.example.repoapp.adapter.ReposAdapter
import com.example.repoapp.databinding.LayoutFragmentRepositoryBinding
import com.example.repoapp.fragment.dialog.ReposLoadingDialog
import com.example.repoapp.viewModel.RepoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryFragment : BaseFragment(
    showToolbar = false,
    showBack = true
) {
    private var loadingDialog : ReposLoadingDialog? = null
    private var adapter : ReposAdapter? = null
    private var _binding: LayoutFragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<RepoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LayoutFragmentRepositoryBinding.bind(
            inflater.inflate(
                R.layout.layout_fragment_repository,
                container,
                false
            )
        )

        setupView()
        setupObservers()
        return binding.root
    }

    private fun setupView(){
        adapter = ReposAdapter { position ->  onListItemClick(position)}
        binding.repoList.adapter = adapter
    }

    private fun setupObservers() {

    }

    private fun onListItemClick(position: Int) {

    }

    private fun showLoading() {
        if (loadingDialog == null) loadingDialog = ReposLoadingDialog()
        loadingDialog?.show(parentFragmentManager, "Repos")
    }
}
