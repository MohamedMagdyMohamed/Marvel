package com.example.marvel.data.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<SeriesSummary>
) : Parcelable
