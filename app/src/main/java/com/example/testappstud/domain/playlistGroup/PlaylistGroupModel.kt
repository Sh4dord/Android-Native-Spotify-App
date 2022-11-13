package com.example.testappstud.domain.playlistGroup

import com.example.testappstud.domain.common.pagination.PaginationModel

data class PlaylistGroupModel(
    var message: String = "",
    var playlists: PaginationModel<PlaylistMinModel>,
) {
}