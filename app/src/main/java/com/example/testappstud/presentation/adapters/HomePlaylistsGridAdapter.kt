package com.example.testappstud.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.testappstud.R
import com.example.testappstud.domain.playlistGroup.PlaylistMinModel


class HomePlaylistsGridAdapter(
    private val playlistList: List<PlaylistMinModel>,
    private val context: Context,
): BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null


    override fun getCount(): Int {
        return playlistList.size
    }

    override fun getItem(index: Int): Any {
        return playlistList[index]
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
        var tempConvertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (tempConvertView == null) {
            tempConvertView = layoutInflater!!.inflate(R.layout.playlist_item, null)
        }

        val playlistImageComponent: ImageView = tempConvertView!!.findViewById(R.id.playlistImage)
        Glide.with(context).load(playlistList[index].images.first().url).into(playlistImageComponent)
        return tempConvertView
    }
}