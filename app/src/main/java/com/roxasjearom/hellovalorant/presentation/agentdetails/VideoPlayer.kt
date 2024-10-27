package com.roxasjearom.hellovalorant.presentation.agentdetails

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(modifier: Modifier = Modifier, videoUrl: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                useController = false
            }
        },
        modifier = modifier.fillMaxWidth(),
        update = { playerView ->
            playerView.player = null

            exoPlayer.setMediaItem(MediaItem.fromUri(videoUrl))
            exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
            exoPlayer.prepare()
            exoPlayer.play()

            playerView.player = exoPlayer
        }
    )
}
