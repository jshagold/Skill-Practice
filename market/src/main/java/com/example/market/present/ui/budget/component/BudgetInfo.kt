package com.example.market.present.ui.budget.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun BudgetInfo(
    categoryName: String,
    budget: Float,
    memo: String,
    datetime: String,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(1.dp, Color.Gray)
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
                    start.linkTo(parent.start)
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
}