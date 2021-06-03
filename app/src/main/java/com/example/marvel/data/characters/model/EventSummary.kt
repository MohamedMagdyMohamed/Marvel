package com.example.marvel.data.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventSummary(
    var resourceURI: String,
    var name: String
) : Parcelable
