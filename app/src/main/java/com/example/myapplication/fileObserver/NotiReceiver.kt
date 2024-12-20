package com.example.myapplication.fileObserver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class NotiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.e("TAG", "onReceive in broadcast", )
        intent.let {
            when(intent.action) {
                DownloadManager.ACTION_DOWNLOAD_COMPLETE -> {
                    val status = intent.getIntExtra(DownloadManager.ACTION_DOWNLOAD_COMPLETE, -1)
                    Log.e("TAG", "onReceive: status:$status", )
                    Toast.makeText(context, "download complete", Toast.LENGTH_SHORT).show()
                }


                else -> {
                    val status = intent.getIntExtra(DownloadManager.ACTION_DOWNLOAD_COMPLETE, -1)
                    Log.e("TAG", "onReceive else: status:$status", )
                    Toast.makeText(context, "download complete", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}