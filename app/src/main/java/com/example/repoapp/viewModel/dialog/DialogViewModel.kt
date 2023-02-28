package com.example.repoapp.viewModel.dialog

import androidx.lifecycle.ViewModel
import com.example.repoapp.fragment.dialog.ReposDialogButton

class DialogViewModel : ViewModel() {

    var title: String? = null
    var message: String? = null
    var positiveButton: ReposDialogButton? = null
    var negativeButton: ReposDialogButton? = null
    var isCancelable: Boolean = false
}