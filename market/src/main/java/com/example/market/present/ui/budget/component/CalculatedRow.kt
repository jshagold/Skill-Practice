package com.example.market.present.ui.budget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.market.R


@Preview
@Composable
fun PreviewCalculatedRow() {
    CalculatedRow()
}


@Composable
fun CalculatedRow(
    modifier: Modifier = Modifier,
    income: Int = 0,
    expense: Int = 0,
    total: Int = 0,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.budget_screen_calculated_row_income)
        )

        Text(
            text = stringResource(R.string.budget_screen_calculated_row_expense)
        )

        Text(
            text = stringResource(R.string.budget_screen_calculated_row_total)
        )
    }
}