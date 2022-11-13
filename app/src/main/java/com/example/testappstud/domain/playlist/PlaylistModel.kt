package com.example.testappstud.domain.playlist

import com.example.testappstud.domain.common.pagination.PaginationModel
import com.example.testappstud.domain.track.TrackInfoModel

data class PlaylistModel(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    var owner: OwnerModel,
    var followers: PlaylistFollowersModel,
    var images: List<PlaylistImageModel>,
    var tracks: PaginationModel<TrackInfoModel>,
)
