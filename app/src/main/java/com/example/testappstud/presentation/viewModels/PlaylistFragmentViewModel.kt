package com.example.testappstud.presentation.viewModels

import androidx.lifecycle.*
import com.example.testappstud.domain.playlist.PlaylistModel
import com.example.testappstud.infrastructure.playlist.PlaylistRepository


@Suppress("UNCHECKED_CAST")
class PlaylistFragmentViewModelFactory(private val playlistId: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistFragmentViewModel(playlistId) as T
    }
}

class PlaylistFragmentViewModel(private val playlistId: String): ViewModel() {
    private val playlistRepository = PlaylistRepository()

    val playlist: LiveData<PlaylistModel> = liveData {
        val data = playlistRepository.findOne(playlistId) // loadUser is a suspend function.
        emit(data)
    }
}