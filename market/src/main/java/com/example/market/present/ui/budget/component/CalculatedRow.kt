package com.example.market.present.ui.budget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.market.R
import com.example.market.present.theme.ExpenseTheme
import com.example.market.present.theme.IncomeTheme
import com.example.market.present.theme.Surface06
import com.example.market.present.theme.Surface07
import com.example.market.present.theme.Surface08
import com.example.market.present.utils.extension.bottomBorder
import com.example.market.present.utils.extension.toPriceFormat


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
            .bottomBorder(1.dp, Surface07)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.budget_screen_calculated_row_income),
                modifier = Modifier
            )
            Text(
                text = income.toPriceFormat(),
                color = IncomeTheme,
                modifier = Modifier
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.budget_screen_calculated_row_expense),
                modifier = Modifier
            )
            Text(
                text = expense.toPriceFormat(),
                color = ExpenseTheme,
                modifier = Modifier
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.budget_screen_calculated_row_total),
                modifier = Modifier
            )
            Text(
                text = total.toPriceFormat(),
                modifier = Modifier
            )
        }
    }
}