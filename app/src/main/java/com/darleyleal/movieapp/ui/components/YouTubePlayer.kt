package com.darleyleal.movieapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.regex.Pattern

@Composable
fun YouTubePlayer(
    youtubeLink: String
) {
    val videoId = extractVideoIdFromLink(youtubeLink)
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(10.dp)),
        factory = {
            val view = YouTubePlayerView(it)
            val fragment = view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        if (videoId != null) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        } else {
                            Toast.makeText(context, "Error in link", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            view
        }
    )
}

fun extractVideoIdFromLink(link: String): String? {
    val pattern =
        "(?<=watch\\?v=|/videos/|embed\\/|" +
                "youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?" +
                "feature=player_embedded&v=|%2Fvideos%2F|embed%2F|" +
                "youtu.be%2F|%2Fv%2F|%2Fe%2F)[^#\\&\\?\\n]*"
    val compiledPattern = Pattern.compile(pattern)
    val matcher = compiledPattern.matcher(link)
    return if (matcher.find()) {
        matcher.group()
    } else {
        null
    }
}