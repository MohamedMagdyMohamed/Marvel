package com.example.marvel.data.characters.model

data class Thumbnail(
    var path: String,
    var extension: String
) {

    fun urlPath(): String {
        return "$path.$extension"
    }
}
