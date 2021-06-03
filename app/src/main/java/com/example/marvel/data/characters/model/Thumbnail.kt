package com.example.marvel.data.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    var path: String,
    var extension: String
) : Parcelable {

    fun urlPath(): String {
        return "$path.$extension"
    }
}
