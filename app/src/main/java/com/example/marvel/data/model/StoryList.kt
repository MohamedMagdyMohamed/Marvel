package com.example.marvel.data.model

data class StoryList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<StorySummary>
)
