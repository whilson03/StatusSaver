package com.olabode.wilson.statussaver.ui.viewers.videosviewer

import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.olabode.wilson.statussaver.R
import com.olabode.wilson.statussaver.databinding.VideosViewerFragmentBinding
import com.olabode.wilson.statussaver.ui.StatusType
import com.olabode.wilson.statussaver.ui.Utils
import com.olabode.wilson.statussaver.ui.model.Status
import java.io.File


class VideosViewerFragment : Fragment() {

    private var mCurrentPosition: Int = 0
    private lateinit var videoView: VideoView
    private val PLAYBACK_TIME = "play_time"

    private lateinit var status: Status
    private lateinit var binding: VideosViewerFragmentBinding
    private lateinit var viewModel: VideoViewerViewModel
    private lateinit var statusType: StatusType


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        status = VideosViewerFragmentArgs.fromBundle(arguments!!).status
        statusType = VideosViewerFragmentArgs.fromBundle(arguments!!).statusType

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }
        binding = VideosViewerFragmentBinding.inflate(inflater)
        videoView = binding.exoPlay

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory: VideoViewerFactory = VideoViewerFactory(status, statusType)
        viewModel = ViewModelProviders.of(this, factory).get(VideoViewerViewModel::class.java)

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.resetErrorMessage()
            }

        })
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_functions, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.download -> {
                Toast.makeText(context, "Saving...", Toast.LENGTH_SHORT).show()
                viewModel.save()
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            }
            R.id.repost -> {
                val intent = Utils.openWhatsApp(context!!, status.path)
                if (Utils.isIntentAvailable(context!!, intent)) {
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.share -> {
                Utils.shareToOtherApp(context!!, status.path)
            }

        }
        return super.onOptionsItemSelected(item)
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
