package com.example.marvel.data.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    var type: String,
    var url: String
) : Parcelable
