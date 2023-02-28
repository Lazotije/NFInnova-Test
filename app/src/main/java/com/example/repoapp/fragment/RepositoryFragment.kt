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
import com.example.repoapp.fragment.dialog.ReposAlertDialog
import com.example.repoapp.fragment.dialog.ReposDialogButton
import com.example.repoapp.fragment.dialog.ReposLoadingDialog
import com.example.repoapp.utils.Strings
import com.example.repoapp.viewModel.RepoViewModel
import com.example.repoapp.vo.Status
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

        viewModel.reposResponse.observe(viewLifecycleOwner) { state ->
            when(state.status) {
                Status.RUNNING -> showLoading()
                Status.SUCCESS -> {
                    loadingDialog?.dismiss()
                }
                Status.FAILED -> {
                    showError(state.error, Strings.get(R.string.error_unexpected))
                }
                Status.EMPTY -> {
                    //do nothing
                }
            }
        }
    }

    private fun onListItemClick(position: Int) {
        viewModel.repoClicked(position)
    }

    private fun showLoading() {
        if (loadingDialog == null) loadingDialog = ReposLoadingDialog()
        loadingDialog?.show(parentFragmentManager, ReposLoadingDialog.tag)
    }

    private fun showError(title: String? = null, message: String?) {
        loadingDialog?.dismiss()
        ReposAlertDialog(title, message, positiveButton = ReposDialogButton(getString(R.string.ok)) {
            viewModel.resetState()
        }
        ).show(parentFragmentManager,ReposLoadingDialog.errorTag )
    }

}
