package com.example.animexplorer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime (
    var name: String,
    var synopsis: String,
    var photo: String,
    var Episode: String,
    var genre: String,
    var duration: String,
    var studio: String,

) : Parcelable
