package com.example.marvel.data.model

data class ComicList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<ComicSummary>
)
