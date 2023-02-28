package com.example.repoapp.fragment.dialog

import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.repoapp.R
import com.example.repoapp.databinding.AlertDialogBinding
import com.example.repoapp.utils.Colors
import com.example.repoapp.viewModel.dialog.DialogViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class ReposAlertDialog(
    private var title: String? = null,
    private var message: String? = null,
    private var positiveButton: ReposDialogButton? = null,
    private var negativeButton: ReposDialogButton? = null,
    isCancelable: Boolean = false
) : DialogFragment() {

    private val viewModel: DialogViewModel by stateViewModel()

    init {
        setCancelable(isCancelable)
    }

    private lateinit var binding: AlertDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Colors.get(R.color.overlay_background)))
        binding = AlertDialogBinding.inflate(inflater)

        if (savedInstanceState != null) {
            viewModel.title?.let { title = it }
            viewModel.message?.let { message = it }
            viewModel.positiveButton?.let { positiveButton = it }
            viewModel.negativeButton?.let { negativeButton = it }
            isCancelable = viewModel.isCancelable
        }

        if (isCancelable) {
            binding.root.setOnClickListener {
                dismiss()
            }
        }

        if (!title.isNullOrBlank()) {
            binding.dialogTitle.apply {
                visibility = View.VISIBLE
                text = title
            }
        }

        if (!message.isNullOrBlank()) {
            binding.dialogMessage.apply {
                visibility = View.VISIBLE
                text = message
            }
        }

        positiveButton?.let {
            binding.dialogPositiveButton.apply {
                visibility = View.VISIBLE
                text = it.text
                setOnClickListener {
                    positiveButton!!.onClickListener?.invoke(
                        DialogInterface.BUTTON_POSITIVE
                    )
                    dismiss()
                }
            }
        }

        negativeButton?.let {
            binding.dialogNegativeButton.apply {
                visibility = View.VISIBLE
                text = it.text
                setOnClickListener {
                    negativeButton!!.onClickListener?.invoke(
                        DialogInterface.BUTTON_NEGATIVE
                    )
                    dismiss()
                }
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.title = title
        viewModel.message = message
        viewModel.positiveButton = positiveButton
        viewModel.negativeButton = negativeButton
        viewModel.isCancelable = isCancelable
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_Dialog_TranslucentStatusBar_NoColor
    }
}