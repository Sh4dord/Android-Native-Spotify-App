package com.example.testappstud.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testappstud.R
import com.example.testappstud.domain.track.TrackModel
import com.example.testappstud.presentation.interfaces.OnItemClickInterface

class PlaylistTrackListAdapter(
    private val trackList: List<TrackModel>,
    private val onItemClick: OnItemClickInterface<TrackModel>,
) :
    RecyclerView.Adapter<PlaylistTrackListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackItem: LinearLayout = itemView.findViewById<LinearLayout>(R.id.trackItem)
        val trackName: TextView = itemView.findViewById<TextView>(R.id.trackName)
        val trackAuthor: TextView = itemView.findViewById<TextView>(R.id.trackAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val trackItemView = inflater.inflate(R.layout.track_item, null)
        return ViewHolder(trackItemView)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: PlaylistTrackListAdapter.ViewHolder, position: Int) {

        val track = trackList[position]
        holder.trackName.text = track.name
        holder.trackAuthor.text = track.artists.first().name

        val hasPreviewUrl = track.preview_url != null
        if (!hasPreviewUrl) holder.trackItem.alpha = 0.3F

        // I had on click event if preview_url == null
        // I do the check when the music have to be launched
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(track)
        }

    }
}