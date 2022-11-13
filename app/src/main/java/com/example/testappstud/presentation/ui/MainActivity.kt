package com.example.testappstud.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testappstud.R
import com.example.testappstud.domain.track.TrackModel
import com.example.testappstud.presentation.interfaces.MusicPlayerInterface
import com.example.testappstud.services.music.MusicService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MusicPlayerInterface {


    var musicService: MusicService = MusicService()

    /** UI View **/
    private lateinit var playerController: ConstraintLayout
    private lateinit var playerControl: ImageView
    private lateinit var playerTrackInfoTitle: TextView
    private lateinit var playerTrackInfoAuthor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.mainActivityFragment, HomeFragment()).commitAllowingStateLoss()
        initViewObjects()
    }


    private fun initViewObjects() {
        playerController = findViewById(R.id.playerController)
        playerControl = findViewById(R.id.playerControl)
        playerTrackInfoTitle = findViewById(R.id.playerInfoName)
        playerTrackInfoAuthor = findViewById(R.id.playerInfoAuthor)
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate()
    }

    override fun start(track: TrackModel) {
        if(track.preview_url == null) {
            Toast.makeText(this, "Impossible to start this music", Toast.LENGTH_SHORT).show()
        }else{
            playerControl.setImageResource(android.R.drawable.ic_media_pause)
            playerController.visibility = View.VISIBLE
            playerTrackInfoTitle.text = track.name
            playerTrackInfoAuthor.text = track.artists.first().name
            playerControl.setOnClickListener {
                if(musicService.toggleSound()) {
                    playerControl.setImageResource(android.R.drawable.ic_media_pause)
                }else{
                    playerControl.setImageResource(android.R.drawable.ic_media_play)
                }
            }
            musicService.playSound(track.preview_url)

        }
    }
}