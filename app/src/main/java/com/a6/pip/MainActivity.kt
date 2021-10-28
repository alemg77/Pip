package com.a6.pip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a6.pip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonVideo1.setOnClickListener {
            playVideo(VIDEO1)
        }

        binding.buttonVideo2.setOnClickListener {
            playVideo(VIDEO2)
        }

        binding.buttonVideo3.setOnClickListener {
            playVideo(VIDEO3)
        }
    }

    private fun playVideo(videoUrl: String) {
        val intent = Intent(this, PipActivity::class.java)
        intent.putExtra("videoUrl", videoUrl)
        startActivity(intent)
    }

    companion object {
        const val VIDEO1 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp42"
        const val VIDEO2 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        const val VIDEO3 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
        const val VIDEO4 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
        const val VIDEO5 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
        const val VIDEO6 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
        const val VIDEO7 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"
        const val VIDEO8 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
        const val VIDEO9 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
        const val VIDEO10 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
        const val VIDEO11 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"
        const val VIDEO12 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
        const val VIDEO13 =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"
    }
}