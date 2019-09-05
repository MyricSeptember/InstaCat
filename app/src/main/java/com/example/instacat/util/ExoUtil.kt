package com.example.instacat.util

import android.content.Context
import android.net.Uri
import com.example.instacat.R
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ExoUtil(context: Context) {

    private lateinit var exoPlayer: SimpleExoPlayer
    private val context = context

    private fun initializeExoplayer() {
        val renderersFactory = DefaultRenderersFactory(
            context,
            null, // drmSessionManager: DrmSessionManager
            DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF
        )

        val trackSelector = DefaultTrackSelector()
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector)

        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        val mediaSource = ExtractorMediaSource(
            Uri.parse("asset:///Meow.mp3"),
            DefaultDataSourceFactory(context, userAgent),
            DefaultExtractorsFactory(),
            null, // eventHandler: Handler
            null
        ) // eventListener: ExtractorMediaSource.EventListener

        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
    }

    private fun releasePlayer() {
        exoPlayer?.let { exoPlayer ->
            exoPlayer.stop()
            exoPlayer.release()
        }
    }

    fun onCreate() {
        if (Util.SDK_INT > 23) {
            initializeExoplayer()
        }
    }

    /**
     * Clears references
     */
    fun onStart() {
        if (Util.SDK_INT > 23) {
            // initializeExoplayer()
        }
    }

    /**
     * Regains references
     */
    fun onResume() {
        if (Util.SDK_INT <= 23) {
            //initializeExoplayer()
        }
    }

    /**
     * Clears references
     */
    fun onPause() {
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    /**
     * Regains references
     */
    fun onStop() {
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }
}