package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.compose.pager.FlipPager
import com.example.myapplication.compose.pager.offsetForPage
import com.example.myapplication.fileObserver.FileDetectingService
import com.example.myapplication.ui.theme.MyApplicationTheme
import eu.wewox.pagecurl.ExperimentalPageCurlApi
import eu.wewox.pagecurl.page.PageCurl
import eu.wewox.pagecurl.page.rememberPageCurlState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPageCurlApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                val scrollState = rememberScrollState()
                
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ){

                        val pages = listOf("One","Two","Three","Four")
                        val pageState = rememberPageCurlState()
                        val coroutineScope = rememberCoroutineScope()
                        PageCurl(
                            modifier = Modifier
                                .size(400.dp)
                                .focusable()
                                .onKeyEvent { keyEvent ->
                                    Log.e("TAG", "CubePager onKeyEvent $keyEvent")

                                    if(keyEvent.type == KeyEventType.KeyDown) {
                                        when(keyEvent.key) {
                                            Key.DirectionLeft -> {
                                                coroutineScope.launch {
                                                    pageState.prev()
                                                }
                                                true
                                            }

                                            Key.DirectionRight -> {
                                                coroutineScope.launch {
                                                    pageState.next()
                                                }
                                                true
                                            }

                                            else -> false
                                        }
                                    } else {
                                        false
                                    }
                                }
                            ,
                            count = pages.size,
                            state = pageState
                        ) { index ->
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.background)
                                    .fillMaxSize()
                            ) {
                                AsyncImage(
                                    model = "https://picsum.photos/id/$index/300/300",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()

                                )

                                Text(
                                    text = "$index", style = MaterialTheme.typography.headlineLarge.copy(
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

//                        ConstraintLayout(
//                            modifier = Modifier
//                                .fillMaxSize()
//                        ) {
//                            val (left, right) = createRefs()
//
//                            Text(
//                                text = "left",
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier
//                                    .constrainAs(left) {
//                                        start.linkTo(parent.start)
//                                        end.linkTo(right.start)
//                                        width = Dimension.fillToConstraints
//                                    }
//                                    .clickable {
//                                        coroutineScope.launch {
//                                            pageState.prev()
//                                        }
//                                    }
//                            )
//                            Text(
//                                text = "right",
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier
//                                    .constrainAs(right) {
//                                        start.linkTo(left.end)
//                                        end.linkTo(parent.end)
//                                        width = Dimension.fillToConstraints
//                                    }
//                                    .clickable {
//                                        coroutineScope.launch {
//                                            pageState.next()
//                                        }
//                                    }
//                            )
//                        }



//                        val state = rememberPagerState(pageCount = { 9 })
//
//                        FlipPager(
//                            state = state,
//                            modifier = Modifier.fillMaxWidth().weight(1f),
//                        ) { page ->
//                            Box(
//                                modifier = Modifier
//                                    .padding(16.dp)
//                                    .clip(RoundedCornerShape(16.dp)),
//                            ) {
//                                AsyncImage(
//                                    model = "https://picsum.photos/id/$page/300/300",
//                                    contentDescription = null,
//                                    contentScale = ContentScale.Crop,
//                                    modifier = Modifier.fillMaxSize()
//                                )
//
//                                Text(
//                                    text = "$page", style = MaterialTheme.typography.headlineLarge.copy(
//                                        color = Color.White,
//                                        fontWeight = FontWeight.Bold,
//                                        shadow = Shadow(
//                                            color = Color.Black.copy(alpha = .6f),
//                                            blurRadius = 30f,
//                                        )
//                                    )
//                                )
//                            }
//                        }

//                        MainScreen()

//                        FlipPagerPractice()

//                        CubePager()
//
//                        BookTurnOverEffect()

                    }
                }
            }
        }

        startForegroundService(Intent(this, FileDetectingService::class.java))
    }
}

@Composable
fun BookTurnOverEffect() {
    var isTurned by remember { mutableStateOf(false) }
    val rotationY by animateFloatAsState(if (isTurned) 180f else 0f, label = "")


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        BookPage(rotationY) {
            isTurned = !isTurned
        }
    }
}

@Composable
fun BookPage(rotationY2: Float, onClick: () -> Unit) {
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds()
            .graphicsLayer {
                rotationY = rotationY2.absoluteValue
                cameraDistance = 8 * density
            }
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() }
                )
            }
    ) {
        // Draw the page content here
        drawRect(color = Color.Blue)
    }
}


@Composable
fun CubePager() {
    val scrollState = rememberScrollState()

    val pagerState = rememberPagerState(pageCount = { 9 })
    val coroutineScope = rememberCoroutineScope()

    var currentPage by remember { mutableStateOf(0) }

    var offsetY by remember { mutableStateOf(0f) }

    val scale by remember {
        derivedStateOf {
            1f - (pagerState.currentPageOffsetFraction.absoluteValue) * .3f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
            ) { page ->
                currentPage = page
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = pagerState.offsetForPage(page)
                            val offScreenRight = pageOffset < 0f
                            val deg = 105f
                            val interpolated = FastOutLinearInEasing.transform(pageOffset)
                            rotationY = min(interpolated * if (offScreenRight) deg else -deg, 90f)

                            transformOrigin = TransformOrigin(
                                pivotFractionX = if (offScreenRight) 0f else 1f,
                                pivotFractionY = .5f
                            )
                        }
                        .drawWithContent {
                            val pageOffset = pagerState.offsetForPage(page)

                            this.drawContent()
                            drawRect(
                                Color.Black.copy(
                                    (pageOffset.absoluteValue * .7f)
                                )
                            )
                        }
                        .background(Color.LightGray)
                        .focusable()
                        .onFocusChanged { focusState ->
                            Log.e("TAG", "CubePager Focused: page=$page")
                            if (focusState.isFocused) {
                                // 포커스가 이동하면 해당 페이지로 스크롤
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(
                                        page = page,
                                        animationSpec = tween(durationMillis = 1000)
                                    )
                                }
                            }
                        }
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page = page + 1,
                                    animationSpec = tween(durationMillis = 1000)
                                )
                            }
                        }
                    ,
//                    .graphicsLayer {
//                        // MAKE THE PAGE NOT MOVE
//                        val pageOffset = state.offsetForPage(page)
//                        translationX = size.width * pageOffset
//
//                        // ADD THE CIRCULAR CLIPPING
//                        val endOffset = state.endOffsetForPage(page)
//
//                        shadowElevation = 20f
//                        shape = CirclePath(
//                            progress = 1f - endOffset.absoluteValue,
//                            origin = Offset(
//                                size.width,
//                                offsetY,
//                            )
//                        )
//                        clip = true
//
//                        // PARALLAX SCALING
//                        val absoluteOffset = state.offsetForPage(page).absoluteValue
//                        val scale = 1f + (absoluteOffset.absoluteValue * .4f)
//
//                        scaleX = scale
//                        scaleY = scale
//
//                        // FADE AWAY
//                        val startOffset = state.startOffsetForPage(page)
//                        alpha = (2f - startOffset) / 2f
//
//                    },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://picsum.photos/id/$page/300/300",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = "Hello", style = MaterialTheme.typography.headlineLarge.copy(
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

        Text(
            text = "currentpage: $currentPage",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

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
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = currentPage - 1,
                                animationSpec = tween(durationMillis = 1000)
                            )
                        }
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
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = currentPage + 1,
                                animationSpec = tween(durationMillis = 1000)
                            )
                        }
                    }
            )
        }
    }
}

class CirclePath(private val progress: Float, private val origin: Offset = Offset(0f, 0f)) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {

        val center = Offset(
            x = size.center.x - ((size.center.x - origin.x) * (1f - progress)),
            y = size.center.y - ((size.center.y - origin.y) * (1f - progress)),
        )
        val radius = (sqrt(
            size.height * size.height + size.width * size.width
        ) * .5f) * progress

        return Outline.Generic(Path().apply {
            addOval(
                Rect(
                    center = center,
                    radius = radius,
                )
            )
        })
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val images = remember {
        mutableStateListOf(
            "https://images.unsplash.com/photo-1694481348806-0b6de4934812?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1471&q=80",
            "https://images.unsplash.com/photo-1694057442309-bfe467bff9a9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80",
            "https://images.unsplash.com/photo-1559803509-40f78353d413?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2564&q=80",
            "https://plus.unsplash.com/premium_photo-1668633086435-a16be494a922?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80"
        )
    }

    val pagerState = rememberPagerState(pageCount = {images.size})
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { idx ->
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .focusable()
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        // 포커스가 이동하면 해당 페이지로 스크롤
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(idx)
                        }
                    }
                }
            ,
            model = ImageRequest.Builder(LocalContext.current).data(images[idx])
                .build(),
            contentDescription = "imagePager",
            contentScale = ContentScale.Crop
        )
    }
} // End of MainScreen()
