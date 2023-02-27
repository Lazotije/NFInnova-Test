package com.example.repoapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repoapp.BaseFragment
import com.example.repoapp.R
import com.example.repoapp.adapter.ReposAdapter
import com.example.repoapp.databinding.LayoutFragmentRepositoryBinding
import com.example.repoapp.fragment.dialog.ReposLoadingDialog
import com.example.repoapp.viewModel.RepoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryFragment : BaseFragment(
    showToolbar = false,
    showBack = false,
    "Repositories"
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModel.getRepos()
    }

    private fun setupView(){
        adapter = ReposAdapter { position ->  onListItemClick(position)}
        binding.repoList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

        viewModel.repoSelected.observe(viewLifecycleOwner) { repo ->
            parentFragment?.findNavController()?.navigate(RepositoryFragmentDirections.actionRepoDetails(repo))
        }
    }

    private fun onListItemClick(position: Int) {
        viewModel.repoClicked(position)
    }

    private fun showLoading() {
        if (loadingDialog == null) loadingDialog = ReposLoadingDialog()
        loadingDialog?.show(parentFragmentManager, "Repos")
    }
}
