package com.example.marvel.data.characters.model

data class ComicList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<ComicSummary>
)
