package com.example.marvel.data.model

data class SeriesList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<SeriesSummary>
)
