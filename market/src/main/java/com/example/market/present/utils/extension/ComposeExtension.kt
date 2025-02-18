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
 * 드래그 중인 아이템의 시각적 효과를 적용하는 함수
 * isDragging - 현재 아이템이 드래그 중인지 확인.
 * offsetOrNull - 드래그 중일 때만 변위값을 가져온다.
 * zIndex - 드래그 중인 아이템을 다른 아이템 위에 표시한다.
 * graphicsLayer - 드래그 중인 아이템에 변위와 확대 효과를 적용한다.
 */
fun Modifier.dragModifier(index: Int, dragAndDropListState: DragDropListState) = composed {
    val isDragging = index == dragAndDropListState.currentIndexOfDraggedItem
    val offsetOrNull = dragAndDropListState.elementDisplacement.takeIf { isDragging }

    Modifier
        .zIndex(if (isDragging) 1f else 0f)
        .graphicsLayer {
            translationY = offsetOrNull ?: 0f
            scaleX = if (isDragging) 1.05f else 1f
            scaleY = if (isDragging) 1.05f else 1f
        }
}

