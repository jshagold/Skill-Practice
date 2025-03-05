package com.example.market.present.ui.budget.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.market.R
import com.example.market.domain.model.enums.BudgetDateType
import com.example.market.present.utils.extension.noRippleClickable
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Preview
@Composable
fun PreviewBudgetHeaderTab() {
    BudgetHeaderTab()
}


@Composable
fun BudgetHeaderTab(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = LocalDate.now(),
    dateType: BudgetDateType = BudgetDateType.MONTH,
    onClickLeft: () -> Unit = {},
    onClickRight: () -> Unit = {},
    onClickDate: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (leftArrow, rightArrow, dateComponent) = createRefs()

        Image(
            painter = painterResource(R.drawable.icon_arrow_left),
            contentDescription = "icon_arrow_left",
            modifier = Modifier
                .padding(10.dp)
                .noRippleClickable { onClickLeft() }
                .constrainAs(leftArrow) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(dateComponent.start)
                }
        )

        Text(
            text = when(dateType) {
                BudgetDateType.YEAR -> selectedDate.format(DateTimeFormatter.ofPattern("yyyy"))
                BudgetDateType.MONTH -> selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"))
                BudgetDateType.DAY -> selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            },
            modifier = Modifier
                .padding(10.dp)
                .noRippleClickable { onClickDate() }
                .constrainAs(dateComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(leftArrow.end)
                    end.linkTo(rightArrow.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.icon_arrow_right),
            contentDescription = "R.drawable.icon_arrow_right",
            modifier = Modifier
                .padding(10.dp)
                .noRippleClickable { onClickRight() }
                .constrainAs(rightArrow) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(dateComponent.end)
                    end
                }
        )
    }
}