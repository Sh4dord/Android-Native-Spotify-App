package com.example.testappstud.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappstud.R
import com.example.testappstud.domain.playlist.PlaylistModel
import com.example.testappstud.domain.track.TrackModel
import com.example.testappstud.other.NumberUtils
import com.example.testappstud.presentation.adapters.PlaylistTrackListAdapter
import com.example.testappstud.presentation.interfaces.MusicPlayerInterface
import com.example.testappstud.presentation.interfaces.OnItemClickInterface
import com.example.testappstud.presentation.viewModels.PlaylistFragmentViewModel
import com.example.testappstud.presentation.viewModels.PlaylistFragmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

class PlaylistFragment(private var playlistId: String) : Fragment(R.layout.fragment_playlist) {

    /** View Model **/
    private val playlistFragmentViewModel: PlaylistFragmentViewModel by viewModels {
        PlaylistFragmentViewModelFactory(
            playlistId
        )
    }

    /** UI View **/
    private lateinit var playlistInfoCardLayout: LinearLayout
    private lateinit var playlistImage: ImageView
    private lateinit var playlistTitle: TextView
    private lateinit var playlistAuthor: TextView
    private lateinit var playlistDescription: TextView
    private lateinit var playlistFollowers: TextView
    private lateinit var playlistTrackList: RecyclerView
    private lateinit var playlistLoader: RelativeLayout

    /** Music Control from Activity **/
    private lateinit var musicPlayerInterface: MusicPlayerInterface;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            musicPlayerInterface = context as MusicPlayerInterface
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewObjects(view)
        listenToLiveData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewObjects(view: View) {
        playlistInfoCardLayout = view.findViewById(R.id.playlistInfoCard)
        playlistImage = view.findViewById(R.id.playlistImage)
        playlistTitle = view.findViewById(R.id.playlistTitle)
        playlistAuthor = view.findViewById(R.id.playlistAuthor)
        playlistDescription = view.findViewById(R.id.playlistDescription)
        playlistFollowers = view.findViewById(R.id.playlistFollower)
        playlistTrackList = view.findViewById(R.id.playlistTrackList)
        playlistTrackList.layoutManager = LinearLayoutManager(requireContext())
        playlistTrackList.setHasFixedSize(true)
        playlistLoader = view.findViewById(R.id.playlistLoader)

    }


    private fun listenToLiveData() {
        // Create the observer which updates the UI.
        val playlistObserver = Observer<PlaylistModel> { newPlaylist ->
            // Update the UI, in this case, a TextView.
            setupPlaylistInfo(newPlaylist)
            playlistTrackList.adapter =
                PlaylistTrackListAdapter(newPlaylist.tracks.items.map { trackInfoModel -> trackInfoModel.track },
                    object : OnItemClickInterface<TrackModel> {
                        override fun onItemClick(item: TrackModel) {
                            musicPlayerInterface.start(item)
                        }
                    })
            playlistLoader.visibility = View.INVISIBLE
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        playlistFragmentViewModel.playlist.observe(viewLifecycleOwner, playlistObserver)


    }

    private fun setupPlaylistInfo(playlistModel: PlaylistModel) {
        playlistInfoCardLayout.visibility = View.VISIBLE
        Glide.with(requireContext()).load(playlistModel.images.first().url).into(playlistImage)
        playlistTitle.text = playlistModel.name
        // TODO : l10n
        playlistAuthor.text = "${resources.getString(R.string.playlistAuthor)} ${playlistModel.owner.displayName}"
        playlistDescription.text = playlistModel.description
        playlistFollowers.text = "${playlistModel.followers.total?.let { NumberUtils.Companion.numberFormatter(it) }} ${resources.getString(R.string.playlistFollowers)}"
    }

}