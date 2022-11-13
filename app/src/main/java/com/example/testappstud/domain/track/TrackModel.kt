package com.example.testappstud.domain.track

data class TrackModel(
    val id: String = "",
    val name: String = "",
    val preview_url: String?,
    val artists: List<ArtistModel>,
)
