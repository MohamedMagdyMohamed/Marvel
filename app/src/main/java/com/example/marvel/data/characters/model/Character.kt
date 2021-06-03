package com.example.marvel.data.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: ArrayList<Url>,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val series: SeriesList
) : Parcelable
