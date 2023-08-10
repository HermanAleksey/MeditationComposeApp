package com.justparokq.feature.music_player.exoplayer

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.justparokq.feature.music_player.R
import com.justparokq.feature.music_player.data.entities.DataSourceMapper
import com.justparokq.feature.music_player.data.entities.LocalRes
import com.justparokq.feature.music_player.data.entities.LocalURL
import com.justparokq.feature.music_player.data.entities.WebURL
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.ui.PlayerNotificationManager.BitmapCallback


class MusicNotificationManger(
    private val context: Context,
    sessionToken: MediaSessionCompat.Token,
    notificationListener: PlayerNotificationManager.NotificationListener,
    private val onNewSongStarted: () -> Unit,
    private val dataSourceMapper: DataSourceMapper,
) {

    private val notificationManager: PlayerNotificationManager

    companion object {
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_CHANNEL_ID = "Music App"
    }

    init {
        val mediaController = MediaControllerCompat(context, sessionToken)
        notificationManager = PlayerNotificationManager.Builder(
            context,
            NOTIFICATION_ID,
            NOTIFICATION_CHANNEL_ID
        )
            .setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            .setSmallIconResourceId(R.drawable.ic_music)
            .setNotificationListener(notificationListener)
            .setChannelNameResourceId(R.string.notification_channel_name)
            .build()
        notificationManager.setMediaSessionToken(sessionToken)
    }

    fun showNotification(player: Player) {
        notificationManager.setPlayer(player)
    }

    private inner class DescriptionAdapter(
        private val mediaController: MediaControllerCompat,
    ) : PlayerNotificationManager.MediaDescriptionAdapter {

        override fun getCurrentContentTitle(player: Player): CharSequence {
            onNewSongStarted()
            return mediaController.metadata.description.title.toString()
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return mediaController.sessionActivity
        }

        override fun getCurrentContentText(player: Player): CharSequence {
            return mediaController.metadata.description.subtitle.toString()
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback,
        ): Bitmap? {
            mediaController.metadata.description.iconUri?.let { uri ->
                val dataSourceType = dataSourceMapper.getDataSourceType(uri.toString())

                when (dataSourceType) {
                    is LocalRes -> {
                        val drawable = context.getDrawable(dataSourceType.resId) ?: return@let
                        return drawableToBitmap(drawable)
                    }
                    is LocalURL -> throw Throwable("Not yet imlemented")
                    is WebURL -> {
                        sourceToBitmap(dataSourceType.url, callback)
                    }
                }
            }

            //todo add beautiful default option
            return null
        }
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap? {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }
        val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun sourceToBitmap(url: String, callback: BitmapCallback) {
        Glide.with(context).asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?,
                ) {
                    callback.onBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit
            })
    }
}