package com.example.repoapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.*

abstract class BaseFragment(
    var showToolbar: Boolean = true,
    var showBack: Boolean = false,
    var title: String? = null
) : Fragment(), DefaultLifecycleObserver {
    interface BaseFragmentInterface {
        fun setToolbarVisible(visible: Boolean)
        fun setToolbarTitle(text: String?)
        fun setNavigationIcon(resId: Int?)
        fun setShowBack(show: Boolean)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(this)
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }

//    override fun onResume(owner: LifecycleOwner) {
//        if (activity is BaseFragmentInterface) {
//            with(activity as BaseFragmentInterface) {
//                setToolbarVisible(showToolbar)
//                setToolbarTitle(title)
//                setShowBack(showBack)
//            }
//        }
//    }

    //todo check
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated() {
        if (activity is BaseFragmentInterface) {
            with(activity as BaseFragmentInterface) {
                setToolbarVisible(showToolbar)
                setToolbarTitle(title)
                setShowBack(showBack)
            }
        }
    }
}
