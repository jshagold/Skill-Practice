package com.example.myapplication.fileObserver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import java.io.File


@Composable
fun ComposeDownloadReceiver(
    action: String,
    onEvent: (intent: Intent?) -> Unit
) {
    // Grab the current context in this part of the UI tree
    val context = LocalContext.current


    // Safely use the latest onSystemEvent lambda passed to the function
    val currentOnEvent by rememberUpdatedState(onEvent)

    // If either context or systemAction changes, unregister and register again
    DisposableEffect(context, action) {
        val intentFilter = IntentFilter(action)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentOnEvent(intent)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(broadcast, intentFilter, Context.RECEIVER_EXPORTED)
        }
        else {
            context.registerReceiver(broadcast, intentFilter)
        }

        // When the effect leaves the Composition, remove the callback
        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }


    Text(
        text = "download",
        modifier = Modifier
            .clickable {
                urlDownloading(context, "https://picsum.photos/200/300".toUri())
            }
    )
}

private val outputFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/test_file.txt"
fun urlDownloading(context: Context, url: Uri) {
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val outputFile: File = File(outputFilePath)
    if (!outputFile.getParentFile()?.exists()!!) {
        outputFile.getParentFile()?.mkdirs()
    }
    val request = DownloadManager.Request(url)
    val pathSegmentList = url.pathSegments
    request.setTitle("다운로드 항목")
    request.setDestinationUri(Uri.fromFile(outputFile))
    request.setAllowedOverMetered(true)

    downloadManager.enqueue(request)
}
