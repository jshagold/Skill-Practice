package com.example.myapplication

import android.app.DownloadManager
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.myapplication.fileObserver.FileDetectingService
import com.example.myapplication.fileObserver.urlDownloading
import com.example.myapplication.level.LevelScreen
import com.example.myapplication.sensor.SensorListener
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.io.File


private val ApkSample: String = "/storage/emulated/0/Download/child_0_9_2.apk"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val builder = VmPolicy.Builder()
//        StrictMode.setVmPolicy(builder.build())

        setContent {
            MyApplicationTheme {
                
                val launcherMultiplePermissions = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) {permissionsMap ->
                    val mapCollection = permissionsMap.values
                    val areGranted: Boolean = if(mapCollection.isNotEmpty()) {
                        mapCollection.reduce { acc, next -> acc && next }
                    } else {
                        false
                    }

                    val permission = if (permissionsMap.isNotEmpty()) {
                        val value = permissionsMap
                            .asSequence()
                            .filter {
                                !it.value
                            }.firstNotNullOfOrNull {
                                it.key
                            }
                        if(value.isNullOrEmpty()) {
                            ""
                        } else {
                            value
                        }
                    } else {
                        ""
                    }

                    /** 권한 요청 후 동의 한 경우 **/

                    /** 권한 요청 후 동의 한 경우 **/
                    if(areGranted) {
                        Log.e("TAG", "권한동의: ")
                    }
                    /** 권한 요청 후 거부 한 경우 **/
                    /** 권한 요청 후 거부 한 경우 **/
                    else {
                        Log.e("TAG", "권한거절: ")
                        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                        intent.setData(Uri.parse("package:$packageName"))
                        startActivity(intent)
                    }
                }



                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Text(
                            text = "permission",
                            modifier = Modifier
                                .clickable {
                                    checkAndRequestPermissions(
                                        context = context,
                                        permissions = getCommonPermissionList(),
                                        launcher = launcherMultiplePermissions,
                                        onNextGranted = {
                                        }
                                    )
                                }
                        )


                        Text(
                            text = "test",
                            modifier = Modifier
                                .clickable {
                                    getApkList(context)
                                    sendBroadcast(Intent(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
                                }
                        )

                        Text(
                            text = "download",
                            modifier = Modifier
                                .clickable {
                                    urlDownloading(context, "https://picsum.photos/200/300".toUri())
                                }
                        )

                        Text(
                            text = "launch",
                            modifier = Modifier
                                .clickable {
                                    launchApk(context, "child_0_9_2.apk")
//                                    Log.e("TAG", "onCreate: findapkfile ${findApkFile(context, "child_0_9_2.apk")}")
                                }
                        )

                        Text(
                            text = "service uri launch",
                            modifier = Modifier
                                .clickable {
                                    val apkUri = FileDetectingService.lastUri

                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        setDataAndType(apkUri, "application/vnd.android.package-archive")
                                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    }
                                    context.startActivity(intent)
                                }
                        )

                        FileScreen()

//                        FunctionScreen()
                    }


                }
            }
        }

        startForegroundService(Intent(this, FileDetectingService::class.java))
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val mageticValue = remember { mutableDoubleStateOf(0.0) }
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    val sensorEventListener = SensorListener(mageticValue)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when(event) {
                Lifecycle.Event.ON_RESUME -> {
                    sensorManager.registerListener(sensorEventListener, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL)
                }
                Lifecycle.Event.ON_PAUSE -> {}
                Lifecycle.Event.ON_STOP -> {}
                Lifecycle.Event.ON_DESTROY -> {
                    sensorManager.unregisterListener(sensorEventListener)
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column {
        Text(
            text = "Hello ${mageticValue.doubleValue}!",
            modifier = modifier
        )

        LevelScreen()
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Greeting("Android")
        }
    }
}

fun getApkList(context: Context) {
    val pm = context.packageManager
    val pkgInfo = pm.getPackageArchiveInfo(ApkSample,0)

    Log.e("TAG", "getApkList: ${pkgInfo?.versionName}")

}

@RequiresApi(Build.VERSION_CODES.Q)
private fun getFiles(context: Context): List<String> {

    val fileList: MutableList<String> = mutableListOf()

    val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.MIME_TYPE,
    )

    val cursor = context.contentResolver.query(
        MediaStore.Downloads.EXTERNAL_CONTENT_URI,
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

            val contentUri = ContentUris.appendId(
                MediaStore.Files.getContentUri("external").buildUpon(),
                id
            ).build()

            Log.e("TAG", "getFiles: in cursor name=$fileName type=$type uri=$contentUri")

            fileList.add(fileName)
//            fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        } while(cursor.moveToNext())

        cursor.close()
    }

    return fileList
}



fun launchApk(context: Context, uri: String) {
    val apkUri = findApkFile(context, uri)

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(apkUri, "application/vnd.android.package-archive")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    context.startActivity(intent)
}

fun getFileList(context: Context): List<String> {
    val outputFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()


    val file = File(Environment.getExternalStorageDirectory().toString() + "/Download")
    val fileList = file.listFiles()
    val fileList2 = file.list()

    return fileList2.map {
        it
    }

    Log.e("TAG", "getFileList: path=$outputFilePath")

    return fileList.map {
        Log.d("TAG", "getFileList: path=${it.path}")
//        Log.d("TAG", "getFileList: uri=${getImageContentUri(context,it.path)}")
        it.name
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun FileScreen() {

    val context = LocalContext.current

    val appState by remember { mutableStateOf(getFiles(context)) }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = (appState.size * 200).dp)
            .background(Color.Gray)
//            .verticalScroll(scrollState)
    ) {
        item {
            Text(
                text = "size:${appState.size}\n\n",
            )
        }

        items(appState.size) {
            Text(
                text = appState[it],
                modifier = Modifier
            )
        }
    }

}

fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    onNextGranted: () -> Unit
) {
    if(permissions.all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }) {
        Log.d("TAG", "checkAndRequestPermissions: 권한이 이미 존재한다.")
        onNextGranted()
    } else {
        launcher.launch(permissions)
        Log.d("TAG", "checkAndRequestPermissions: 권한 요청")
    }
}

fun getCommonPermissionList(): Array<String> {
    val permissionList: MutableList<String> = mutableListOf()


    permissionList.addAll(callPermissionArray29)

    return permissionList.toTypedArray()
}

@RequiresApi(Build.VERSION_CODES.R)
private val callPermissionArray29 = arrayOf(
    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
)


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

