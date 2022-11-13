package com.example.testappstud.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testappstud.domain.playlistGroup.PlaylistGroupModel
import com.example.testappstud.infrastructure.playlist.PlaylistRepository


class HomeFragmentViewModel(): ViewModel() {

    private val playlistRepository = PlaylistRepository()

    val playlistGroup: LiveData<PlaylistGroupModel> = liveData {
        val data = playlistRepository.findAll()
        emit(data)
    }
}