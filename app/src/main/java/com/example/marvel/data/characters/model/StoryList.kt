package com.example.marvel.data.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<StorySummary>
) : Parcelable
