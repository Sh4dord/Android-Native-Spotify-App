package com.example.testappstud.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testappstud.domain.playlistGroup.PlaylistGroupModel
import com.example.testappstud.infrastructure.playlist.PlaylistRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Provider


class HomeFragmentViewModel: ViewModel() {

    private var playlistRepository = PlaylistRepository()

    val playlistGroup: LiveData<PlaylistGroupModel> = liveData {
        val data = playlistRepository.findAll()
        emit(data)
    }
}