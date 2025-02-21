package com.example.market.present.ui.budget.component.dragdrop

import android.util.Log
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.market.present.utils.extension.dragModifier
import com.example.market.present.utils.extension.move
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@Composable
fun DragDropList(
    modifier: Modifier,
    listSize: Int,
    changeListIndex: (from: Int, to: Int) -> Unit,
    isActivate: Boolean = true,
    content: @Composable (modifier: Modifier, index: Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val dragAndDropListState =
        rememberDragDropListState(lazyListState) { from, to ->
            if(from >= 0 && to >= 0) {
                changeListIndex(from, to)
            }
        }

    val coroutineScope = rememberCoroutineScope()
    var overScrollJob by remember { mutableStateOf<Job?>(null) }


    LazyColumn(
        modifier = modifier
            .pointerInput(isActivate) {
                Log.e("TAG", "DragDropList: isActivate: $isActivate", )
                if(isActivate) {
                    detectDragGesturesAfterLongPress(
                        onDrag = { change, offset ->
                            change.consume()
                            dragAndDropListState.onDrag(offset)

                            if(overScrollJob?.isActive == true) return@detectDragGesturesAfterLongPress

                            dragAndDropListState
                                .checkOverscroll()
                                .takeIf { it != 0f }
                                ?.let {
                                    overScrollJob = coroutineScope.launch {
                                        dragAndDropListState.lazyListState.scrollBy(it)
                                    }
                                } ?: run { overScrollJob?.cancel() }
                        },
                        onDragStart = { offset ->
                            dragAndDropListState.onDragStart(offset)
                        },
                        onDragEnd = {
                            dragAndDropListState.onDragInterrupted()
                        },
                        onDragCancel = {
                            dragAndDropListState.onDragInterrupted()
                        },
                    )
                }
            },
        state = dragAndDropListState.lazyListState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(listSize) { index ->
            content(
                Modifier.dragModifier(index, dragAndDropListState),
                index
            )
        }
    }

}