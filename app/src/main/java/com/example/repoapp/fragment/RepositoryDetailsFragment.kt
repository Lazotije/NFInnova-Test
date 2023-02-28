package com.example.repoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.repoapp.BaseFragment
import com.example.repoapp.R
import com.example.repoapp.adapter.TagsAdapter
import com.example.repoapp.databinding.LayoutFragmentRepositoryDetailsBinding
import com.example.repoapp.fragment.dialog.ReposAlertDialog
import com.example.repoapp.fragment.dialog.ReposDialogButton
import com.example.repoapp.fragment.dialog.ReposLoadingDialog
import com.example.repoapp.utils.Strings
import com.example.repoapp.viewModel.RepoDetailsViewModel
import com.example.repoapp.vo.Status
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsFragment : BaseFragment(
    title = Strings.get(R.string.repo_details)
) {
    private val args: RepositoryDetailsFragmentArgs by navArgs()
    private var _binding: LayoutFragmentRepositoryDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<RepoDetailsViewModel>()
    private var adapter : TagsAdapter? = null
    private var loadingDialog : ReposLoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LayoutFragmentRepositoryDetailsBinding.bind(
            inflater.inflate(
                R.layout.layout_fragment_repository_details,
                container,
                false
            )
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        fetchRepoDetails()
        fetchTags()
    }

    private fun setupAdapter() {
        adapter = TagsAdapter()
        binding.rvTagList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.repoDetails.observe(viewLifecycleOwner) {
            binding.apply {
                tvUsername.text = args.repo.owner.login
                tvForksNum.text = it.forksCount.toString()
                tvWatchersNum.text = it.watchersCount.toString()
                tvRepoName.text = args.repo.name
            }

            Picasso.get().load(args.repo.owner.avatar_url).
            into(binding.ivAvatar)
        }

        viewModel.tags.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

        viewModel.repoDetailsResponse.observe(viewLifecycleOwner) { state ->
            when (state.status) {
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

    private fun fetchRepoDetails() {
        viewModel.getDetails(args.repo.name)
    }

    private fun fetchTags() {
        viewModel.getTags(args.repo.name)
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