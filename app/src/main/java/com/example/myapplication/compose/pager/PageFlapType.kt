package com.example.myapplication.compose.pager

import androidx.compose.ui.graphics.Shape

internal sealed class PageFlapType(val shape: Shape) {
    data object Left : PageFlapType(LeftShape)
    data object Right : PageFlapType(RightShape)
}