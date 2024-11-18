package com.example.myapplication.function

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun FunctionScreen() {
    val context = LocalContext.current

    val appList = getInstallApps(context = context)


    val scrollState = rememberScrollState()

    val appState by remember { mutableStateOf(appList) }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = (appState.size * 200).dp)
//            .verticalScroll(scrollState)
    ) {
        item {
            Text(
                text = "size:${appState.size}\n\n",
            )
        }

        items(appState.size) {
            Text(
                text = appState[it].name,
                modifier = Modifier
                    .clickable {
                        appState[it].visible = !appState[it].visible
                    }
            )

            if(appState[it].visible) {
                Text(
                    text = appList[it].grantedPermissions,
                    color = Color.Red,
                    modifier = Modifier.background(Color.Gray)
                )
                Text(
                    text = appList[it].requestedPermissions,
                    modifier = Modifier.background(Color.Gray)
                )
            }
        }
    }

}


private fun getInstallApps(context: Context): MutableList<AppInfo> {

    val packageManager = context.packageManager

    val intent = Intent(Intent.ACTION_MAIN, null).apply {
        this.addCategory(Intent.CATEGORY_LAUNCHER)
    }

    val appList = packageManager.queryIntentActivities(intent, 0)

    val appInfoList = mutableListOf<AppInfo>()


    for (info in appList) {
        val appPackageName = info.activityInfo.packageName
        val appName = info.loadLabel(packageManager).toString()

        val packageInfo: PackageInfo = packageManager.getPackageInfo(appPackageName, PackageManager.GET_PERMISSIONS)
        val requestedPermissions = packageInfo.requestedPermissions
        var requestedPermissionString: String = ""

        var grantedPermissionString: String = ""

        if(requestedPermissions != null) {
            for(permission in requestedPermissions) {
                requestedPermissionString += "$permission\n"
            }

            for (i in packageInfo.requestedPermissions.indices) {
                if (packageInfo.requestedPermissionsFlags[i] and PackageInfo.REQUESTED_PERMISSION_GRANTED != 0) {
                    grantedPermissionString += "${packageInfo.requestedPermissions[i]}\n"
                }
            }
        }


        val app = AppInfo(
            name = appName,
            packageName = appPackageName,
            grantedPermissions = grantedPermissionString,
            requestedPermissions = requestedPermissionString,
            visible = false
        )

        appInfoList.add(app)
    }
    return appInfoList
}

data class AppInfo(
    val name: String,
    val packageName: String,
    val grantedPermissions: String,
    val requestedPermissions: String,
    var visible: Boolean
)

