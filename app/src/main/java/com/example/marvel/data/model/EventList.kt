package com.example.marvel.data.model

data class EventList(
    var available: Int,
    var returned: Int,
    var collectionURI: String,
    var items: ArrayList<EventSummary>
)
