package com.example.market.present.ui.budget.component.dragdrop

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset


// [드래그 앤 드롭 기능에 필요한 상태를 관리하는 객체를 제공한다.]
// lazyListState: LazyListState - LazyColumn의 스크롤 상태를 관리하는 LazyListState 객체
// onMove: (Int, Int) -> Unit - 아이템이 이동될 때 호출되는 콜백함수. 두개의 Int 매개변수 (from, to)를 받아서 아이템의 이동을 처리한다.
@Composable
fun rememberDragDropListState(
    lazyListState: LazyListState,
    onMove: (Int, Int) -> Unit
): DragDropListState {
    return remember { DragDropListState(lazyListState, onMove) }
}


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

    // [주어진 아이템 위치에 해당하는 LazyListItemInfo를 반환한다.]
    // 현재 화면에 보이는 아이템들 중에서 특정 위치의 아이템 정보를 찾는데 사용된다.
    private fun LazyListState.getVisibleItemInfo(itemPosition: Int): LazyListItemInfo? {
        return this.layoutInfo.visibleItemsInfo.getOrNull(itemPosition - this.firstVisibleItemIndex)
    }

    // [LazyListItemInfo의 끝 offset을 계산한다.]
    // 아이템의 시작 offset에 size를 더해서 아이템의 끝 위치를 구한다.
    private val LazyListItemInfo.offsetEnd: Int
        get() = this.offset + this.size

    //  [드래그를 시작한 아이템의 초기 위치 정보를 제공]
    //  initialDraggingElement가 null이 아닐 때만 값을 반환
    //  아이템의 시작위치(offset)와 끝 위치(offsetEnd)를 Pair로 반환
    private val initialOffsets: Pair<Int, Int>?
        get() = initialDraggingElement?.let { Pair(it.offset, it.offsetEnd) }

    //  [현재 드래그 중인 아이템의 변위(displacement)를 계산한다.]
    //  currentIndexOfDraggedItem이 null이 아닌 경우에만 계산을 시작한다.
    //  lazyListState.getVisibleItemInfo(it)로 현재 드래그 중인 아이템의 정보를 가져온다.
    //  초기 위치(initialDraggingElement?.offset)에 현재까지의 드래그 거리(draggingDistance)를 더하고, 현재 아이템의 오프셋(itemInfo.offset)을 뺀다.
    val elementDisplacement: Float?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfo(it)
        }?.let { itemInfo ->
            (initialDraggingElement?.offset?: 0f).toFloat() + draggingDistance - itemInfo.offset
        }

    //  [현재 드래그 중인 아이템의 정보를 제공한다.]
    //  currentIndexOfDraggedItem이 null이 아닌 경우, 해당 인덱스의 아이템 정보를 반환한다.
    private val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfo(it)
        }


    // [드래그 시작 시 초기 상태를 설정한다.]
    // 1. 터치된 위치(offset)를 기반으로 드래그 시작된 아이템을 찾는다.
    // 2. 해당 아이템을 initialDraggingElement로 설정한다.
    // 3. 현재 드래그 중인 아이템의 인덱스를 currentIndexOfDraggedItem에 저장한다.
    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo
            .firstOrNull { item -> offset.y.toInt() in item.offset..item.offsetEnd }
            ?.also {
                initialDraggingElement = it
                currentIndexOfDraggedItem = it.index
            }
    }

    // [드래그가 중단되었을 때 상태를 초기화한다.]
    // 1. 모든 드래그 관련 상태 변수를 초기값으로 리세하나다ㅣ
    fun onDragInterrupted() {
        initialDraggingElement = null
        currentIndexOfDraggedItem = null
        draggingDistance = 0f
    }

    // [드래그 중 아이템의 위치를 업데이트하고 필요시 아이템을 이동시킨다.]
    // 1. 드래그 거리를 업데이트한다.
    // 2. 드래그 중인 아이템의 새 위치를 계산한다.
    // 3. 새 위치에 있는 다른 아이템과 위치를 바꿔야 하는지 확인한다.
    // 4. 필요시 onMove 콜백을 호출하여 아이템 위치를 변경한다.
    fun onDrag(offset: Offset) {
        draggingDistance += offset.y

        initialOffsets?.let { (top,bottom) ->
            val startOffset = top.toFloat() + draggingDistance
            val endOffset = bottom.toFloat() + draggingDistance

            currentElement?.let { current ->
                lazyListState.layoutInfo.visibleItemsInfo
                    .filterNot { item ->
                        item.offsetEnd < startOffset || item.offset > endOffset || current.index == item.index
                    }
                    .firstOrNull { item ->
                        val delta = startOffset - current.offset
                        when {
                            delta < 0 -> item.offset > startOffset
                            else -> item.offsetEnd < endOffset
                        }
                    }
            }?.also { item ->
                currentIndexOfDraggedItem?.let { current ->
                    onMove.invoke(current, item.index)
                }
                currentIndexOfDraggedItem = item.index
            }
        }
    }


    // [리스트의 경계를 넘어가는 드래그를 감지하고 오버스크롤 값을 계산한다.]
    // 1. 드래그 중인 아이템이 뷰의 상단이나 하단을 넘어갔는지 확인한다.
    // 2. 넘어간 경우, 얼마나 넘어갔는지 계산하여 반환한다.
    // 3. 이 값은 LazyColumn을 자동으로 스크롤하는데 사용된다.
    fun checkOverscroll(): Float {
        return initialDraggingElement?.let {
            val startOffset = it.offset + draggingDistance
            val endOffset = it.offsetEnd + draggingDistance
            return@let when {
                draggingDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff -> diff > 0 }
                draggingDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { diff -> diff < 0 }
                else -> null
            }
        } ?: 0f
    }
}