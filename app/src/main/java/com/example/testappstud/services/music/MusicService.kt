package com.example.testappstud.services.music

import android.media.AudioAttributes
import android.media.MediaPlayer

class MusicService() {
    var mMediaPlayer: MediaPlayer? = null

    private fun initPlayerWithNewTrack(url: String) {
        mMediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare()
            start()
        }
    }

    fun playSound(url: String?) {
        if (url != null) {
            // We stop the previous music
            if(mMediaPlayer != null) {
                mMediaPlayer!!.stop()
            }

            // And Start the new one / first one
            initPlayerWithNewTrack(url)


        }else{
            // if mediaPlayer is loaded, resume music
            mMediaPlayer?.start()
        }

    }

    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    fun toggleSound() : Boolean {
        return if (mMediaPlayer?.isPlaying == true) {
            mMediaPlayer?.pause()
            false
        } else {
            mMediaPlayer?.start()
            true
        }
    }

    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}