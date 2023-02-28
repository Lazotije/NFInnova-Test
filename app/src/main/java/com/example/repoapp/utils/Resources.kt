package com.example.repoapp.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.repoapp.RepoApplication

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return RepoApplication.instance.getString(stringRes, *formatArgs)
    }
}

object Drawables {
    fun get(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(RepoApplication.instance, drawableRes)
    }
}

object Colors {
    fun get(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(RepoApplication.instance, colorRes)
    }
}