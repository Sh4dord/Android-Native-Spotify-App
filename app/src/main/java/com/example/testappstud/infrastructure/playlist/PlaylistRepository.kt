package com.example.testappstud.infrastructure.playlist

import android.util.Log
import com.example.testappstud.other.Constants
import com.example.testappstud.domain.playlist.PlaylistModel
import com.example.testappstud.domain.playlistGroup.PlaylistGroupModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class PlaylistRepository {

    companion object {
        const val PLAYLIST_GROUP_ENDPOINT = "${Constants.BASE_URL}/browse/featured-playlists"
        const val PLAYLIST_ENDPOINT = "${Constants.BASE_URL}/playlists/"
    }

    suspend fun findOne(playlistId: String): PlaylistModel {
        val result = withContext(Dispatchers.IO) {
            val url = URL(PLAYLIST_ENDPOINT + playlistId)

            val stringResult = url.readText()

            Log.d("[Playlist] - getOne - ", stringResult)

            Gson().fromJson(stringResult, PlaylistModel::class.java)

        }
        Log.d("[Playlist] - getOne - ", result.toString())
        return result
    }

    suspend fun findAll(): PlaylistGroupModel {
        val result = withContext(Dispatchers.IO) {
            val url = URL(PLAYLIST_GROUP_ENDPOINT)

            val stringResult = url.readText()

            Log.d("[Playlist] - getAll - ", stringResult)

            Gson().fromJson(stringResult, PlaylistGroupModel::class.java)

        }
        return result
    }
}