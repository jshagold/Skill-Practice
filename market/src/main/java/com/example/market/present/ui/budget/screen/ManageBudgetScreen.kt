package com.example.market.present.ui.budget.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.market.R
import com.example.market.domain.model.Budget
import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.model.enums.BudgetType
import com.example.market.present.theme.Surface07
import com.example.market.present.ui.budget.component.BudgetTab
import com.example.market.present.ui.budget.component.BudgetTypeRadioBtn
import com.example.market.present.ui.budget.component.CategorySelectBottomSheet
import com.example.market.present.ui.budget.component.CategoryTab
import com.example.market.present.ui.budget.component.DatePickerModal
import com.example.market.present.ui.budget.component.DatetimeTab
import com.example.market.present.ui.budget.component.MemoTab
import com.example.market.present.ui.budget.component.TimePickerModal
import com.example.market.present.ui.budget.viewmodel.ManageBudgetViewModel
import com.example.market.present.ui.shared.component.TopTabBackBtn
import com.example.market.present.utils.checkStringToFloat
import java.time.LocalDateTime


@Preview
@Composable
fun PreviewManageBudgetScreen() {
    ManageBudgetScreen(
        createBudget = {_,_ ->}
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
        selectedDateTime = uiState.selectedDateTime,
        tabCategoryTab = {
            viewModel.openCloseCategoryBottomSheet(true)
        },
        tabDateTimeTab = {
            viewModel.openCloseDatePicker(true)
        },
        onFocusCategoryTab = uiState.isOpenCategoryBottomSheet,
        createBudget = { budget, memo ->
            viewModel.createBudget(budget, memo)
            navController.popBackStack()
        },
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

    if(uiState.isOpenDatePicker) {
        DatePickerModal(
            initialLocalDateTime = uiState.selectedDateTime,
            onDateSelected = { localDateTime ->
                viewModel.openCloseDatePicker(false)
                viewModel.openCloseTimePicker(true)
                viewModel.selectDateTime(localDateTime)
            },
            onDismiss = {
                viewModel.openCloseDatePicker(false)
            }
        )
    }

    if(uiState.isOpenTimePicker) {
        TimePickerModal(
            initialLocalDateTime = uiState.selectedDateTime,
            onConfirm = { localDateTime ->
                viewModel.openCloseTimePicker(false)
                viewModel.selectDateTime(localDateTime)
            },
            onDismiss = {
                viewModel.openCloseTimePicker(false)
            },
        )
    }
}


@Composable
fun ManageBudgetScreen(
    modifier: Modifier = Modifier,
    toBackScreen: () -> Unit = {},
    budgetList: List<Budget> = listOf(),
    selectedCategory: List<BudgetCategory?> = listOf(),
    selectedDateTime: LocalDateTime? = null,
    tabCategoryTab: () -> Unit = {},
    tabDateTimeTab: () -> Unit = {},
    onFocusCategoryTab: Boolean = false,
    createBudget: (budget: Float, memo: String) -> Unit,
    deleteBudget: (budgetId: Long) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val selectedType = remember { mutableStateOf(BudgetType.EXPENSE) }
    val inputBudget = remember { mutableStateOf("") }
    val inputMemo = remember { mutableStateOf("") }

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

            DatetimeTab(
                modifier = Modifier,
                inputDate = selectedDateTime,
                mainColor = selectedType.value.color,
                onClickTab = {
                    tabDateTimeTab()
                }
            )

            MemoTab(
                modifier = Modifier,
                inputText = inputMemo,
                mainColor = selectedType.value.color
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                enabled = checkStringToFloat(inputBudget.value),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonColors(
                    containerColor = selectedType.value.color,
                    contentColor = Color.White,
                    disabledContainerColor = Surface07,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    createBudget(
                        inputBudget.value.toFloat(),
                        inputMemo.value,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.manage_budget_screen_create_btn))
            }

        }

    }
}