package com.example.market.present.ui.budget.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.market.present.utils.extension.noRippleClickable

@Composable
fun BudgetInfo(
    modifier: Modifier = Modifier,
    budgetId: Long,
    categoryName: String,
    budget: Float,
    memo: String,
    datetime: String,
    deleteFlag: Boolean = false,
    deleteBudget: (budgetId: Long) -> Unit = {}
) {
    var visible by remember { mutableStateOf(false) }


    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (infoComponent, deleteComponent) = createRefs()
        val density = LocalDensity.current

        ConstraintLayout(
            modifier = Modifier
                .padding(
                    vertical = 5.dp
                )
                .border(1.dp, Color.Gray)
                .padding(5.dp)
                .constrainAs(infoComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(deleteComponent.start)
                    width = Dimension.fillToConstraints
                }
                .noRippleClickable {
                    if(deleteFlag) {
                        visible = !visible
                    }
                }
        ) {
            val (categoryComponent, budgetComponent, memoComponent, datetimeComponent) = createRefs()
            Text(
                text = "카테고리 : $categoryName",
                modifier = Modifier
                    .constrainAs(categoryComponent) {
                        top.linkTo(parent.top)
                        bottom.linkTo(budgetComponent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = "금액 : $budget",
                color = if(budget >= 0) Color.Red else Color.Blue,
                modifier = Modifier
                    .constrainAs(budgetComponent) {
                        top.linkTo(categoryComponent.bottom)
                        bottom.linkTo(memoComponent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = "메모 : $memo",
                modifier = Modifier
                    .constrainAs(memoComponent) {
                        top.linkTo(budgetComponent.bottom)
                        bottom.linkTo(datetimeComponent.top)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "작성날짜 : $datetime",
                modifier = Modifier
                    .constrainAs(datetimeComponent) {
                        top.linkTo(memoComponent.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(deleteComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(infoComponent.end)
                    end.linkTo(parent.end)
                }
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = slideInHorizontally {
                    // Slide in from 40 dp from the top.
                    with(density) { -40.dp.roundToPx() }
                } + expandHorizontally (
                    // Expand from the top.
                    expandFrom = Alignment.Start
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.3f
                ),
                exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut(),
                ) {
                Image(
                    painter = painterResource(R.drawable.icon_close),
                    contentDescription = "icon_close",
                    colorFilter = ColorFilter.tint(Color.Red),
                    modifier = Modifier
                        .noRippleClickable {
                            deleteBudget(budgetId)
                        }
                        .padding(15.dp)

                )
            }
        }

    }



}