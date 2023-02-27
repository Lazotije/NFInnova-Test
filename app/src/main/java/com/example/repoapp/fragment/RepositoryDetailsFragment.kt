package com.example.repoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.repoapp.R
import com.example.repoapp.adapter.TagsAdapter
import com.example.repoapp.databinding.LayoutFragmentRepositoryDetailsBinding
import com.example.repoapp.viewModel.RepoDetailsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsFragment : Fragment() {
    private val args: RepositoryDetailsFragmentArgs by navArgs()
    private var _binding: LayoutFragmentRepositoryDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<RepoDetailsViewModel>()
    private var adapter : TagsAdapter? = null

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
    }

    private fun fetchRepoDetails() {
        viewModel.getDetails(args.repo.name)
    }

    private fun fetchTags() {
        viewModel.getTags(args.repo.name)
    }
}