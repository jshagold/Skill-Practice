package com.example.market.present.ui.category.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.market.R

@Composable
fun CategoryControlTab(
    modifier: Modifier,
    text: String,
    isSet: Boolean,
    onClickBtn: () -> Unit = {}
) {

    val density = LocalDensity.current

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (gearComponent, contentsComponent) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(gearComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(contentsComponent.start)
                }
        ) {
            AnimatedVisibility(
                visible = isSet,
                enter = slideInHorizontally {
                    // Slide in from -40 dp from the end.
                    with(density) { 40.dp.roundToPx() }
                } + expandHorizontally (
                    // Expand from
                    expandFrom = Alignment.Start
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.3f
                ),
                exit = slideOutHorizontally() + shrinkHorizontally(
                    shrinkTowards = Alignment.Start
                ) + fadeOut(),
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_gear),
                    contentDescription = "icon_close",
                    colorFilter = ColorFilter.tint(Color.Red),
                    modifier = Modifier
                        .padding(10.dp)

                )
            }
        }


        ConstraintLayout(
            modifier = Modifier
                .border(1.dp,Color.Gray, RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(10.dp)
                .constrainAs(contentsComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(gearComponent.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            val (textComponent, deleteBtn) = createRefs()

            Text(
                text = text,
                modifier = Modifier
                    .constrainAs(textComponent) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )

            Image(
                painter = painterResource(R.drawable.icon_close),
                contentDescription = "icon_close",
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier
                    .clickable {
                        onClickBtn()
                    }
                    .constrainAs(deleteBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
    }

}