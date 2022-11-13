package com.example.testappstud.domain.playlistGroup

import com.example.testappstud.domain.playlist.PlaylistImageModel

/// Like playlist but with minimum info for PlaylistGroup
data class PlaylistMinModel(
    var id: String = "",
    var images: List<PlaylistImageModel>,
)