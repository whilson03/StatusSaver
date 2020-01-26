package com.olabode.wilson.statussaver.ui.viewers.videosviewer

import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.olabode.wilson.statussaver.databinding.VideosViewerFragmentBinding
import com.olabode.wilson.statussaver.ui.model.Status
import java.io.File


class VideosViewerFragment : Fragment() {

    private var mCurrentPosition: Int = 0
    private lateinit var videoView: VideoView
    private val PLAYBACK_TIME = "play_time"

    private lateinit var status: Status
    private lateinit var binding: VideosViewerFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        status = VideosViewerFragmentArgs.fromBundle(arguments!!).status

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }
        binding = VideosViewerFragmentBinding.inflate(inflater)
        videoView = binding.exoPlay


        return binding.root
    }


    private fun releasePlayer() {
        videoView.stopPlayback()
    }


    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    private fun getMedia(path: String): Uri? {
        return Uri.fromFile(
            File(path)
        )
    }

    private fun initializePlayer() {
        val controller = MediaController(context)
        videoView.setMediaController(controller)
        videoView.setVideoURI(getMedia(status.path))
        videoView.start()
        if (mCurrentPosition > 0) {
            videoView.seekTo(mCurrentPosition)
        } else {
            // Skipping to 1 shows the first frame of the video.
            videoView.seekTo(1)
        }

        videoView.setOnCompletionListener(OnCompletionListener {
            Toast.makeText(
                requireContext(), "Playback completed",
                Toast.LENGTH_SHORT
            ).show()
            videoView.seekTo(1)
        })
    }


    override fun onStop() {
        super.onStop()
        releasePlayer()
    }


    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYBACK_TIME, videoView.currentPosition)
    }

}
