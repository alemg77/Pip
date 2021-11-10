package com.a6.pip

import android.app.PictureInPictureParams
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.a6.pip.databinding.FragmentPipBinding

private const val ARG_URL = "video_url"

class PipFragment : Fragment() {

    private var url: String? = null

    private lateinit var videoUrl: String

    private lateinit var binding: FragmentPipBinding

    private lateinit var pictureInPictureParams: PictureInPictureParams.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPipBinding.inflate(inflater)

        videoUrl = url.toString()

        binding.pipButtom.setOnClickListener {
            pictureInPictureMode()
        }

        setVideoView()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        if (binding.videoView.isPlaying) {
            binding.videoView.stopPlayback()
        }
    }

    private fun setVideoView() {

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)

        val videoUri = Uri.parse(videoUrl)

        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(videoUri)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParams = PictureInPictureParams.Builder()
        }

        binding.videoView.setOnPreparedListener {
            it.start()
        }
    }

    private fun pictureInPictureMode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //val rational = Rational(binding.videoView.width, binding.videoView.height)
            val rational = Rational(192, 108)
            pictureInPictureParams.setAspectRatio(rational).build()
            requireActivity().enterPictureInPictureMode(pictureInPictureParams.build())

        } else {
            binding.pipButtom.visibility = View.GONE
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)

        if (isInPictureInPictureMode) {
            Log.d(TAG, "onPictureInPictureChanged: Enter PIP")
            binding.pipButtom.visibility = View.GONE
            (requireActivity() as PipActivity).hideActionBar()
        } else {
            Log.d(TAG, "onPictureInPictureChanged: Exit PIP")
            binding.pipButtom.visibility = View.VISIBLE
            (requireActivity() as PipActivity).showActionBar()
        }
    }

    companion object {

        const val TAG = "TAGG"

        @JvmStatic
        fun newInstance(url: String) =
            PipFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, url)
                }
            }
    }
}