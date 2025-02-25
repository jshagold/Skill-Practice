package com.example.market.present.ui.shared.component

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

class SystemUi {
}

/** 상단 Status Bar 색상 변경 함수 **/
@Composable
fun SetStatusBarColor(color: Color) {
    val view = LocalView.current
    SideEffect {
        val activity = view.context as Activity

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsetsControllerCompat(activity.window, view).isAppearanceLightStatusBars = true
        } else {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        activity.window.statusBarColor = color.toArgb()

    }
}