package com.a6.pip

import android.app.PictureInPictureParams
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.a6.pip.databinding.ActivityPipBinding

class PipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPipBinding

    private lateinit var videoUrl: String

    private lateinit var pictureInPictureParams: PictureInPictureParams.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVideoView(intent)

        pictureInPictureParams

        binding.pipButtom.setOnClickListener {
            pictureInPictureMode()
        }

        Log.d("TAGG", videoUrl)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!isInPictureInPictureMode) {
                pictureInPictureMode()
            } else {

            }

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            setVideoView(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        if (binding.videoView.isPlaying) {
            binding.videoView.stopPlayback()
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)

        if (isInPictureInPictureMode) {
            Log.d(TAG, "onPictureInPictureChanged: Enter PIP")
            binding.pipButtom.visibility = View.GONE
            supportActionBar?.hide()
        } else {
            Log.d(TAG, "onPictureInPictureChanged: Exit PIP")
            binding.pipButtom.visibility = View.VISIBLE
            supportActionBar?.show()
        }

    }

    private fun setVideoView(intent: Intent) {

        videoUrl = intent.getStringExtra("videoUrl").toString()

        val mediaController = MediaController(this)
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

            val rational = Rational(binding.videoView.width, binding.videoView.height)
            pictureInPictureParams.setAspectRatio(rational).build()
            enterPictureInPictureMode(pictureInPictureParams.build())

        } else {
            binding.pipButtom.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "TAGG"
    }


}