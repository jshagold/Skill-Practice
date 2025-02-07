package com.example.market.present.ui.budget.component.dragdrop

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DragDropListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    //  현재 드래그 중인 아이템의 이동거리를 저장
    private var draggingDistance by mutableFloatStateOf(0f)

    //  드래그를 시작한 아이템의 초기 정보를 저장
    private var initialDraggingElement by mutableStateOf<LazyListItemInfo?>(null)

    // 현재 드래그 중인 아이템의 index를 저장
    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)

    // 주어진 아이템 위치에 해당하는 LazyListItemInfo를 반환한다.
    // 현재 화면에 보이는 아이템들 중에서 특정 위치의 아이템 정보를 찾는데 사용된다.
    private fun LazyListState.getVisibleItemInfo(itemPosition: Int): LazyListItemInfo? {
        return this.layoutInfo.visibleItemsInfo.getOrNull(itemPosition - this.firstVisibleItemIndex)
    }

    // LazyListItemInfo의 끝 offset을 계산한다.
    // 아이템의 시작 offset에 size를 더해서 아이템의 끝 위치를 구한다.
    private val LazyListItemInfo.offsetEnd: Int
        get() = this.offset + this.size

    //  드래그를 시작한 아이템의 초기 위치 정보를 제공
    //  initialDraggingElement가 null이 아닐 때만 값을 반환
    //  아이템의 시작위치(offset)와 끝 위치(offsetEnd)를 Pair로 반환
    private val initialOffsets: Pair<Int, Int>?
        get() = initialDraggingElement?.let { Pair(it.offset, it.offsetEnd) }

    //  현재 드래그 중인 아이템의 변위(displacement)를 계산한다.
    //  currentIndexOfDraggedItem이 null이 아닌 경우에만 계산을 시작한다.
    //  lazyListState.getVisibleItemInfo(it)로 현재 드래그 중인 아이템의 정보를 가져온다.
    //  초기 위치(initialDraggingElement?.offset)에 현재까지의 드래그 거리(draggingDistance)를 더하고, 현재 아이템의 오프셋(itemInfo.offset)을 뺀다.
    val elementDisplacement: Float?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfo(it)
        }?.let { itemInfo ->
            (initialDraggingElement?.offset?: 0f).toFloat() + draggingDistance - itemInfo.offset
        }

    //  현재 드래그 중인 아이템의 정보를 제공한다.
    //  currentIndexOfDraggedItem이 null이 아닌 경우, 해당 인덱스의 아이템 정보를 반환한다.
    private val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfo(it)
        }
}