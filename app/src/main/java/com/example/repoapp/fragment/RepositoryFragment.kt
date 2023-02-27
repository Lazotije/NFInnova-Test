package com.example.repoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repoapp.BaseFragment
import com.example.repoapp.R
import com.example.repoapp.databinding.LayoutFragmentRepositoryBinding

class RepositoryFragment : BaseFragment(
    showToolbar = false,
    showBack = true
) {
    private var _binding : LayoutFragmentRepositoryBinding? = null
    private val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         _binding = LayoutFragmentRepositoryBinding.bind(inflater.inflate(R.layout.layout_fragment_repository, container, false))
        return binding.root
    }


}
