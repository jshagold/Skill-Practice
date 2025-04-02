package com.example.market.present.ui.shared.component

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.market.present.theme.ProgressBg
import com.example.market.present.theme.ProgressOrange
import com.example.market.present.theme.ProgressOrangeBg


@Preview
@Composable
fun PreviewMoaProgressBar() {

    Column(
        modifier = Modifier
            .background(White)
    ) {

        val trigger = remember { mutableStateOf(false) }
        MoaProgressBar(
            afterCallback = {
                Log.e("TAG", "PreviewMoaProgressBar: Callback", )
            },
            textWithStyles = listOf(
                Pair("2초 후", SpanStyle(color = ProgressOrange)),
                Pair(" ", SpanStyle(color = Black)),
                Pair("다음 기사가 재생됩니다.", SpanStyle(color = Black))
            ),
            animationStartTrigger = trigger,
            modifier = Modifier
        )


        Button(
            onClick = {
                trigger.value = !trigger.value
            }
        ) {
            Text(text = "trigger ${trigger.value}")
        }
    }

}


@Composable
fun MoaProgressBar(
    modifier: Modifier = Modifier,
    afterCallback: () -> Unit = {},
    animationMillis: Int = 2000,
    textWithStyles: List<Pair<String, SpanStyle>> = listOf(),
    barWidth: Dp = 280.dp,
    barHeight: Dp = 30.dp,
    animationStartTrigger: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (progressBar, textComponent) = createRefs()
        ProgressBar(
            afterCallback = afterCallback,
            chartWidth = barWidth,
            chartHeight = barHeight,
            animationStartTrigger = animationStartTrigger.value,
            animationMillis = animationMillis,
            modifier = Modifier
                .constrainAs(progressBar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


    }
}

/**
 * 원형 그래프
 * @param arcBackgroundColor: Color - 먼저 그려져있는 호의 색
 * @param strokeSize: Float - 호의 두께
 * @param representValue: Float - 표시할 값 (0~360f)
 * @param chartSize: Dp - 그래프 크기
 */
@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    afterCallback: () -> Unit = {},
    chartWidth: Dp = 200.dp,
    chartHeight: Dp = 30.dp,
    backgroundColor: Color = ProgressBg,
    borderColor: Color = ProgressOrange,
    borderSize: Dp = 1.dp,
    fillColor: Color = ProgressOrangeBg,
    animationMillis: Int = 2000,
    animationStartTrigger: Boolean = false
) {
    val progressAnimate = remember { Animatable(0f) }

    // 특정 값으로 색을 채우는 Animation
    LaunchedEffect(animationStartTrigger) {
        progressAnimate.snapTo(0f)
        progressAnimate.animateTo(
            targetValue = 100f,
            animationSpec = tween(durationMillis = animationMillis, easing = LinearEasing)
        )

        afterCallback()
    }

    Canvas(
        modifier = Modifier
            .size(
                width = chartWidth,
                height = chartHeight
            )
            .then(modifier)
    ) {
        // Background
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(
                x = borderSize.toPx()/2,
                y = borderSize.toPx()/2,
            ),
            size = Size(
                width = size.width - borderSize.toPx(),
                height = size.height - borderSize.toPx(),
            ),
            cornerRadius = CornerRadius(size.height),
            style = Stroke(width = (1f), cap = StrokeCap.Round)
        )

        // Border
        drawRoundRect(
            color = borderColor,
            topLeft = Offset(
                x = borderSize.toPx()/2,
                y = borderSize.toPx()/2,
            ),
            size = Size(
                width = size.width - borderSize.toPx(),
                height = size.height - borderSize.toPx(),
            ),
            cornerRadius = CornerRadius(size.height),
            style = Stroke(width = (borderSize.toPx()), cap = StrokeCap.Round)
        )

        // Value
        drawRoundRect(
            color = fillColor,
            topLeft = Offset(
                x = borderSize.toPx()/2,
                y = borderSize.toPx()/2,
            ),
            size = Size(
                width = (size.width.times(progressAnimate.value*0.01f)) - borderSize.toPx(),
                height = size.height - borderSize.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = size.height,
                y = size.height
            ),
        )
    }
}