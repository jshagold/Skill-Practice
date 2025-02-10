package com.example.market.present.utils.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex
import com.example.market.present.ui.budget.component.dragdrop.DragDropListState


/**
 * Ripple 효과가 없는 clickable Extension
 */
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


/**
 *
 */
//fun Modifier.dragModifier(index: Int, dragAndDropListState: DragDropListState) = composed {
//    val isDragging = index == dragAndDropListState.currentIndexOfDraggedItem
//    val offsetOrNull = dragAndDropListState.ele
//
//    Modifier
//        .zIndex()
//        .graphicsLayer {
//
//        }
//}

