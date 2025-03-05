package com.example.market.present.ui.shared.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.market.R
import com.example.market.present.utils.extension.noRippleClickable
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Preview
@Composable
fun PreviewYearMonthPicker() {
    YearMonthPicker()
}


@Composable
fun YearMonthPicker(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = LocalDate.now(),
    clickClose: () -> Unit = {},
    clickDate: (date: LocalDate) -> Unit = {}
) {
    val displayDate = remember { mutableStateOf(selectedDate) }

    Card(
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = modifier
            .padding(30.dp)
    ) {

        
        Column(
            modifier = Modifier
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(10.dp)
            ) {
                val (title, thisMonth, close) = createRefs()
                Text(
                    text = stringResource(R.string.year_month_picker_title),
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                )
                
                Text(
                    text = stringResource(R.string.year_month_picker_today),
                    modifier = Modifier
                        .noRippleClickable {
                            clickDate(LocalDate.now())
                        }
                        .constrainAs(thisMonth) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(close.start)
                        }
                )

                Image(
                    painter = painterResource(R.drawable.icon_close),
                    contentDescription = "R.drawable.icon_close",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .noRippleClickable {
                            clickClose()
                        }
                        .padding(5.dp)
                        .constrainAs(close) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                )
            }
            
            ConstraintLayout(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                val (leftArrow, rightArrow, dateComponent) = createRefs()
                createHorizontalChain(leftArrow,dateComponent,rightArrow, chainStyle = ChainStyle.SpreadInside)
                val barrier = createBottomBarrier(leftArrow,rightArrow,dateComponent)

                Image(
                    painter = painterResource(R.drawable.icon_arrow_left),
                    contentDescription = "icon_arrow_left",
                    modifier = Modifier
                        .padding(10.dp)
                        .noRippleClickable {
                            displayDate.value = displayDate.value.minusYears(1)
                        }
                        .constrainAs(leftArrow) {
                            top.linkTo(parent.top)
                            bottom.linkTo(barrier)
                            start.linkTo(parent.start)
                            end.linkTo(dateComponent.start)
                        }
                )

                Text(
                    text = displayDate.value.format(DateTimeFormatter.ofPattern("yyyy")),
                    modifier = Modifier
                        .padding(10.dp)
                        .constrainAs(dateComponent) {
                            top.linkTo(parent.top)
                            bottom.linkTo(barrier)
                            start.linkTo(leftArrow.end)
                            end.linkTo(rightArrow.start)
                        }
                )

                Image(
                    painter = painterResource(R.drawable.icon_arrow_right),
                    contentDescription = "R.drawable.icon_arrow_right",
                    modifier = Modifier
                        .padding(10.dp)
                        .noRippleClickable {
                            displayDate.value = displayDate.value.plusYears(1)
                        }
                        .constrainAs(rightArrow) {
                            top.linkTo(parent.top)
                            bottom.linkTo(barrier)
                            start.linkTo(dateComponent.end)
                            end.linkTo(parent.end)
                        }
                )

                val monthList = createRef()

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .constrainAs(monthList) {
                            top.linkTo(barrier)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    val lines = 3
                    val lineElementCount = 4
                    repeat(lines) { line ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            for (i in 1..4) {
                                Text(
                                    text = "${i + line*lineElementCount}" + stringResource(R.string.month),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .weight(1f)
                                        .noRippleClickable {
                                            clickDate(LocalDate.of(displayDate.value.year, i + line*lineElementCount, displayDate.value.dayOfMonth))
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
        
    }
}