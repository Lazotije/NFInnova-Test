package com.example.repoapp.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.repoapp.R
import com.example.repoapp.databinding.LoadingViewBinding

class ReposLoadingDialog : DialogFragment() {
    private var _binding: LoadingViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoadingViewBinding.bind(
            inflater.inflate(
                R.layout.loading_view,
                container, false
            )
        )
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_Dialog_TranslucentStatusBar_NoColor
    }
}