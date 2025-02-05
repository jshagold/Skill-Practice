package com.example.market.present.ui.budget.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.market.present.ui.budget.viewmodel.ManageBudgetViewModel
import com.example.market.present.ui.component.EditText


@Preview
@Composable
fun PreviewManageBudgetScreen() {
    ManageBudgetScreen()
}


@Composable
fun ManageBudgetRoute(
    viewModel: ManageBudgetViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    ManageBudgetScreen(
        createBudget = viewModel
    )
}


@Composable
fun ManageBudgetScreen(
    modifier: Modifier = Modifier,
    createBudget: (categoryId: Int, budget: String, memo: String, datetime: String) -> Unit,
    deleteBudget: () -> Unit = {},
) {

    val inputBudget = remember { mutableStateOf("") }
    val inputMemo = remember { mutableStateOf("") }
    val inputDatetime = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "Manage Budget")
        EditText(
            placeholder = "Budget",
            inputText = inputBudget
        )
        EditText(
            placeholder = "Memo",
            inputText = inputMemo
        )
        EditText(
            placeholder = "DateTime",
            inputText = inputDatetime
        )
        Button(
            onClick = {
                createBudget(
                    1,
                    inputBudget.value,
                    inputMemo.value,
                    inputDatetime.value ,
                )
            }
        ) {
            Text(text = "create")
        }
    }

}