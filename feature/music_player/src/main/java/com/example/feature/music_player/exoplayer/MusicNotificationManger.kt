package com.example.feature.music_player.exoplayer

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.feature.music_player.R
import com.example.feature.music_player.other.Constants.NOTIFICATION_CHANNEL_ID
import com.example.feature.music_player.other.Constants.NOTIFICATION_ID
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager

class MusicNotificationManger(
    private val context: Context,
    sessionToken: MediaSessionCompat.Token,
    notificationListener: PlayerNotificationManager.NotificationListener,
    private val newSongCallback: () -> Unit,
) {

    private val notificationManager: PlayerNotificationManager

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
            newSongCallback()
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
            Log.e(
                this.javaClass.name.toString(),
                "getCurrentLargeIcon; player:$player, " +
                        "mediaController.metadata.description.iconUri:${mediaController.metadata.description.iconUri}"
            )
            val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
            val mCanvas = Canvas(bitmap)
            mCanvas.drawColor(Color.GREEN)

            //todo here pass bitmap for notification
            Glide.with(context).asBitmap()
                .load(mediaController.metadata.description.iconUri)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?,
                    ) {
                        callback.onBitmap(bitmap)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) = Unit
                })
            return bitmap
        }
    }

    fun sourceToBitmap(str: String) {

    }
}