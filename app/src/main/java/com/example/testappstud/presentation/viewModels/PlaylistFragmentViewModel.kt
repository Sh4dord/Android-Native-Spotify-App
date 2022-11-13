package com.example.testappstud.presentation.viewModels

import androidx.lifecycle.*
import com.example.testappstud.domain.playlist.PlaylistModel
import com.example.testappstud.infrastructure.playlist.PlaylistRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class PlaylistFragmentViewModelFactory(private val playlistId: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistFragmentViewModel(playlistId) as T
    }
}


class PlaylistFragmentViewModel(private val playlistId: String): ViewModel() {
    private var playlistRepository = PlaylistRepository()

    val playlist: LiveData<PlaylistModel> = liveData {
        val data = playlistRepository.findOne(playlistId) // loadUser is a suspend function.
        emit(data)
    }
}