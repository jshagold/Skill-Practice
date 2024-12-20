package com.example.myapplication.fileObserver

import android.app.DownloadManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri
import com.example.myapplication.MainActivity
import com.example.myapplication.findApkFile

class FileDetectingService : Service() {

    private var contentObserver: ContentObserver? = null


    companion object {
        var lastUri: Uri? = null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate() {
        super.onCreate()

        setChannel()

        contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean, uri: Uri?) {
                super.onChange(selfChange, uri)

                uri?.let {
                    if(isFileDownloadComplete(applicationContext, it)) {

                        if(uri == lastUri) {
                            Log.e("TAG", "onChange: 중복이벤트", )
                            return
                        }

                        lastUri = uri

                        Log.e("TAG", "다운로드 완료: fileName=${getFileName(applicationContext, uri)}", )
                        launchApk(applicationContext, uri)
                    } else {

                        Log.e("TAG", "다운로드 진행중: fileName=${getFileName(applicationContext, uri)}", )
                    }
                }
            }
        }

        this.contentResolver.registerContentObserver(MediaStore.Downloads.EXTERNAL_CONTENT_URI, true, contentObserver!!)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(contentObserver != null) {
            this.contentResolver.unregisterContentObserver(contentObserver!!)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun setChannel() {

        val text = ""
        val messageIntent = Intent()
        messageIntent.setClass(this, MainActivity::class.java)
        messageIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        val notifyId: Int = 1
        val channelId: String = "channel_1"
        val channelName: String = "file detecting"
        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
        notificationChannel.setSound(null, null)
        notificationChannel.setShowBadge(false)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        val notificationBuilder = Notification.Builder(this, channelId)
        notificationBuilder.setWhen(System.currentTimeMillis())
        notificationBuilder.setShowWhen(true)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentText(text)
        notificationBuilder.setSubText("")
        notificationBuilder.setOngoing(true)
        val pIntent = PendingIntent.getActivity(
            this,
            0,
            messageIntent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        notificationBuilder.setContentIntent(pIntent)

        val notification = notificationBuilder.build()
        startForeground(notifyId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getFileName(context: Context, uri: Uri): String? {
        var fileInfo: String? = null

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.OWNER_PACKAGE_NAME,
        )

        val cursor = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )


        if(cursor != null && cursor.moveToFirst()) {

            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                val fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
                val type = cursor.getStringOrNull(cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE)) ?: continue
                val data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA))
//                val packageName = getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.OWNER_PACKAGE_NAME))

                val contentUri = ContentUris.appendId(
                    MediaStore.Files.getContentUri("external").buildUpon(),
                    id
                ).build()

                Log.e("TAG", "getFiles: in cursor name=$fileName type=$type uri=$contentUri data=$data\n packageName=$packageName")

                fileInfo = "id=$id fileName=$fileName type=$type uri=$contentUri"
            } while(cursor.moveToNext())

            cursor.close()
        }




        return fileInfo
    }

    fun getUri(context: Context, uri: Uri): Uri? {
        var fileUri: Uri? = null

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATA,
        )

        val cursor = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )

        if(cursor != null && cursor.moveToFirst()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
            val fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
            val type = cursor.getStringOrNull(cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE))
            val data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA))

            val contentUri = ContentUris.appendId(
                MediaStore.Files.getContentUri("external").buildUpon(),
                id
            ).build()

            Log.e("TAG", "getFiles: in cursor name=$fileName type=$type uri=$contentUri data=$data")

            fileUri = contentUri

            cursor.close()
        }

        return fileUri
    }


    fun launchApk(context: Context, uri: Uri) {

        val fileUri = getUri(context, uri)

        if(fileUri != null) {
            Log.e("TAG", "launchApk: fileUri is NOT null", )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
        } else {
            Log.e("TAG", "launchApk: fileUri is null", )
        }
    }

    fun getPackageNameFromApk(context: Context, uri: Uri): String? {
        var data: String = ""

        val projection = arrayOf(
            MediaStore.Files.FileColumns.DATA,
        )

        val cursor = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )


        if(cursor != null && cursor.moveToFirst()) {

            do {
                data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA))
            } while(cursor.moveToNext())

            cursor.close()
        }


        val pm = packageManager
        val packageInfo = pm.getPackageArchiveInfo(data,0)


        return packageInfo?.packageName
    }

    fun isFileDownloadComplete(context: Context, fileUri: Uri): Boolean {
        val projection = arrayOf(
            MediaStore.Downloads._ID,
            MediaStore.Downloads.SIZE
        )

        context.contentResolver.query(fileUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val fileSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Downloads.SIZE))
                return fileSize > 0 // 크기가 0보다 큰 경우 다운로드 완료로 간주
            }
        }

        return false // 다운로드 중이거나 실패한 경우
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun findApkFile(context: Context, fileName: String): Uri? {
        val projection = arrayOf(
            MediaStore.Downloads._ID,
            MediaStore.Downloads.DISPLAY_NAME
        )

        val selection = "${MediaStore.Downloads.DISPLAY_NAME} LIKE ?"
        val selectionArgs = arrayOf(fileName)

        val uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI

        context.contentResolver.query(
            uri,
            projection,
            selection,
            selectionArgs,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID))
                return ContentUris.withAppendedId(uri, id)
            }
        }

        return null // 파일을 찾지 못한 경우
    }

}