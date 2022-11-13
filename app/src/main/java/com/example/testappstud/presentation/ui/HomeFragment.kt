package com.example.testappstud.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.testappstud.R
import com.example.testappstud.domain.playlistGroup.PlaylistGroupModel
import com.example.testappstud.presentation.adapters.HomePlaylistsGridAdapter
import com.example.testappstud.presentation.viewModels.HomeFragmentViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    /** View Model **/
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    /** UI View **/
    private lateinit var homeDailyMessageText: TextView
    private lateinit var homePlaylistsGrid: GridView
    private lateinit var homeLoader: RelativeLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewObjects(view)
        setupGridViewWithLiveData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewObjects(view: View) {
        homeDailyMessageText = view.findViewById(R.id.homeDailyMessageText)
        homePlaylistsGrid = view.findViewById(R.id.homePlaylistsGrid)
        homeLoader = view.findViewById(R.id.homeLoader)
    }

    private fun setupGridViewWithLiveData() {
        // Create the observer which updates the UI.
        val playlistObserver = Observer<PlaylistGroupModel> { newPlaylist ->
            // Update the UI, in this case, a TextView.
            homeDailyMessageText.text = newPlaylist.message
            homePlaylistsGrid.adapter = HomePlaylistsGridAdapter(newPlaylist.playlists.items, requireContext())
            homePlaylistsGrid.onItemClickListener = OnItemClickListener { parent, v, position, id ->
                onPlaylistPressed(newPlaylist.playlists.items[position].id)
            }
            homeLoader.visibility = View.INVISIBLE
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        homeFragmentViewModel.playlistGroup.observe(viewLifecycleOwner, playlistObserver)
    }

    private fun onPlaylistPressed(playlistId: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.mainActivityFragment, PlaylistFragment(playlistId)).addToBackStack(null).commit()
    }
}