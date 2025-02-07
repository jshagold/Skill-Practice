package com.example.market.present.ui.budget.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.domain.model.Budget
import com.example.market.present.theme.PastelYellow
import com.example.market.present.ui.budget.component.BudgetInfo
import com.example.market.present.ui.budget.viewmodel.BudgetViewModel
import com.example.market.present.ui.category.component.CategoryTab
import com.example.market.present.ui.category.component.CreateBtn


@Preview
@Composable
fun PreviewBudgetScreen() {
    BudgetScreen()
}

@Composable
fun BudgetRoute(
    viewModel: BudgetViewModel = hiltViewModel(),
    navigateToManageBudget: () -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BudgetScreen(
        total = uiState.totalIncome,
        balance = uiState.balance,
        budgetList = uiState.budgetList,
        navigateToManageBudget = navigateToManageBudget
    )
}


@Composable
fun BudgetScreen(
    modifier: Modifier = Modifier,
    total: Float = 0f,
    balance: Float = 0f,
    budgetList: List<Budget> = listOf(),
    navigateToManageBudget: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Budget"
            )
            Text(
                text = "total : $total"
            )
            Text(
                text = "balance : $balance"
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp
                    )
            ) {
                items(budgetList.size) { index ->
                    BudgetInfo(
                        budgetId = budgetList[index].budgetId,
                        categoryName = budgetList[index].categoryName,
                        budget = budgetList[index].budget,
                        memo = budgetList[index].memo,
                        datetime = budgetList[index].inputDateTime,
                    )
                }
            }
        }

        CreateBtn(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            onClickBtn = {
                navigateToManageBudget()
            }
        )
    }

}