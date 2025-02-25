package com.example.market.present.ui.budget.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.market.domain.model.Budget
import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.model.enums.BudgetType
import com.example.market.present.ui.budget.component.BudgetInfo
import com.example.market.present.ui.budget.component.BudgetTab
import com.example.market.present.ui.budget.component.BudgetTypeRadioBtn
import com.example.market.present.ui.budget.component.CategorySelectBottomSheet
import com.example.market.present.ui.budget.component.CategoryTab
import com.example.market.present.ui.budget.component.MemoTab
import com.example.market.present.ui.budget.viewmodel.ManageBudgetViewModel
import com.example.market.present.ui.shared.component.EditText
import com.example.market.present.ui.shared.component.TopTabBackBtn
import com.example.market.present.utils.checkStringToFloat


@Preview
@Composable
fun PreviewManageBudgetScreen() {
    ManageBudgetScreen(
        createBudget = {_,_,_ ->}
    )
}


@Composable
fun ManageBudgetRoute(
    viewModel: ManageBudgetViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ManageBudgetScreen(
        toBackScreen = { navController.popBackStack() },
        budgetList = uiState.budgetList,
        selectedCategory = listOf(uiState.selectedCategory),
        tabCategoryTab = {
            viewModel.openCloseCategoryBottomSheet(true)
        },
        onFocusCategoryTab = uiState.isOpenCategoryBottomSheet,
        createBudget = viewModel::createBudget,
        deleteBudget = viewModel::deleteBudget
    )

    if(uiState.isOpenCategoryBottomSheet) {
        CategorySelectBottomSheet(
//            mainColor = ,
            categoryList = uiState.categoryList,
            onDismiss = {
                viewModel.openCloseCategoryBottomSheet(false)
            },
            onClickCategory = viewModel::selectCategory
        )
    }
}


@Composable
fun ManageBudgetScreen(
    modifier: Modifier = Modifier,
    toBackScreen: () -> Unit = {},
    budgetList: List<Budget> = listOf(),
    selectedCategory: List<BudgetCategory?> = listOf(),
    tabCategoryTab: () -> Unit = {},
    onFocusCategoryTab: Boolean = false,
    createBudget: (budget: Float, memo: String, datetime: String) -> Unit,
    deleteBudget: (budgetId: Long) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val selectedType = remember { mutableStateOf(BudgetType.EXPENSE) }
    val inputBudget = remember { mutableStateOf("") }
    val inputMemo = remember { mutableStateOf("") }
    val inputDatetime = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            }
    ) {
        TopTabBackBtn(
            text = "Manage Budget",
            mainColor = selectedType.value.color,
            toBackScreen = toBackScreen
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            BudgetTypeRadioBtn(
                typeList = BudgetType.entries,
                selectedType = selectedType
            )

            BudgetTab(
                modifier = Modifier,
                inputBudget = inputBudget,
                mainColor = selectedType.value.color
            )

            CategoryTab(
                modifier = Modifier,
                mainColor = selectedType.value.color,
                tabContents = tabCategoryTab,
                selectedCategory = selectedCategory,
                onFocus = onFocusCategoryTab
            )

            MemoTab(
                modifier = Modifier,
                inputText = inputMemo,
                mainColor = selectedType.value.color
            )

            EditText(
                placeholder = "DateTime",
                inputText = inputDatetime,
                keyboardType = KeyboardType.Decimal
            )
            Button(
                enabled = checkStringToFloat(inputBudget.value),
                onClick = {
                    createBudget(
                        inputBudget.value.toFloat(),
                        inputMemo.value,
                        inputDatetime.value,
                    )
                }
            ) {
                Text(text = "create")
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(10.dp),
                thickness = 2.dp,
                color = Color.Black
            )

            LazyColumn(
                modifier = Modifier
                    .heightIn(max = budgetList.size.times(150.dp))
                    .padding(
                        horizontal = 10.dp
                    )
            ) {
                item {
                    Text(
                        text = "Budget List size:${budgetList.size}"
                    )
                }

                items(budgetList.size) { index ->
                    BudgetInfo(
                        budgetId = budgetList[index].budgetId,
                        categoryName = budgetList[index].categoryName,
                        budget = budgetList[index].budget,
                        memo = budgetList[index].memo,
                        datetime = budgetList[index].inputDateTime,
                        deleteFlag = true,
                        deleteBudget = deleteBudget,
                    )
                }
            }
        }

    }
}