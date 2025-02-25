package com.example.market.present.ui.budget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.market.domain.model.enums.BudgetType
import com.example.market.present.ui.shared.component.RadioButton


@Preview
@Composable
fun PreviewBudgetTypeRadioBtn() {
    BudgetTypeRadioBtn()
}



@Composable
fun BudgetTypeRadioBtn(
    modifier: Modifier = Modifier,
    typeList: List<BudgetType> = listOf(),
    selectedType: MutableState<BudgetType> = remember { mutableStateOf(BudgetType.EXPENSE) }
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        typeList.forEach { budgetType ->
            RadioButton(
                text = stringResource(budgetType.stringResource),
                mainColor = budgetType.color,
                isActive = budgetType == selectedType.value,
                onClick = {
                    selectedType.value = budgetType
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}