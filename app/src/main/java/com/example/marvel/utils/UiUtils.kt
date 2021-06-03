package com.example.marvel.utils

import android.content.Context

object UiUtils {

    fun getDimen(context: Context, resId: Int): Float {
        return context.resources.getDimension(resId)
    }
}
