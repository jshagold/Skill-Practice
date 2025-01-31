package com.example.myapplication.compose.pager

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.min

inline val Dp.px: Float
    @Composable get() = with(LocalDensity.current) { this@px.toPx() }

@Composable
fun FlipPagerPractice() {
    val scrollState = rememberScrollState()

    val pagerState = rememberPagerState(pageCount = { 9 })
    val coroutineScope = rememberCoroutineScope()

    // Android TV Drag
    var accumulatedDrag by remember { mutableStateOf(0f) } // 드래그 누적 거리
    val dragThreshold = 400.dp.px // 페이지 전환에 필요한 최소 드래그 거리


    val overscrollAmount = remember { mutableFloatStateOf(0f) }
    val animatedOverscrollAmount by animateFloatAsState(
        targetValue = overscrollAmount.floatValue / 500,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        Box(
            modifier = Modifier
                .background(Color.White)
                .size(400.dp)
                .align(alignment = Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onDragStart = {
                                accumulatedDrag = 0f // 드래그 시작 시 초기화
                            },
                            onDragEnd = {
                                // 드래그 종료 시, 페이지 전환 결정
                                val targetPage = when {
                                    accumulatedDrag > dragThreshold -> pagerState.currentPage - 1
                                    accumulatedDrag < -dragThreshold -> pagerState.currentPage + 1
                                    else -> pagerState.currentPage
                                }

                                // 유효한 페이지로 이동
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(targetPage.coerceIn(0 until pagerState.pageCount))
                                }
                                accumulatedDrag = 0f
                            },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume() // 이벤트 소비
                                accumulatedDrag += dragAmount // 드래그 거리 누적

                                // 페이지를 드래그한 만큼 이동
                                coroutineScope.launch {
                                    pagerState.scroll {
                                        scrollBy(-dragAmount)
                                    }
                                }
                            }
                        )
                    }
            ) { page ->
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = pagerState.offsetForPage(page)
                            translationX = size.width * pageOffset

//                            shape = object : Shape {
//                                override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
//                                    Outline.Rectangle(Rect(0f, 0f, size.width / 2, size.height))
//                            }

//                            clip = true

                            rotationY = -min(
                                (pagerState.endOffsetForPage(page) * 180f).coerceIn(-90f..0f),
                                animatedOverscrollAmount.coerceAtLeast(0f) * -20f
                            )
                        }
//                        .drawWithContent {
//                            graphicsLayer.record {
//                                this@drawWithContent.drawContent()
//                            }
//                            drawLayer(graphicsLayer)
//                        }
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://picsum.photos/id/$page/300/300",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = "$page", style = MaterialTheme.typography.headlineLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = .6f),
                                blurRadius = 30f,
                            )
                        )
                    )
                }
            }
        }


        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (left, right) = createRefs()

            Text(
                text = "left",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(left) {
                        start.linkTo(parent.start)
                        end.linkTo(right.start)
                        width = Dimension.fillToConstraints
                    }
            )
            Text(
                text = "right",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(right) {
                        start.linkTo(left.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )
        }
    }

}


fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}

