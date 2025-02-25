package com.example.market.present.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.market.present.ui.category.viewmodel.CreateCategoryViewModel
import com.example.market.present.ui.shared.component.EditText

@Composable
fun CreateCategoryRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateCategoryViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    CreateCategoryScreen(
        modifier = modifier,
        onClickCreateBtn = viewModel::createCategory,
        afterCompleteCreate = { navController.popBackStack() }
    )
}


@Composable
fun CreateCategoryScreen(
    modifier: Modifier = Modifier,
    onClickCreateBtn: (categoryName: String) -> Unit = {},
    afterCompleteCreate: () -> Unit = {},
) {
    val inputText = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "Create Category")
        EditText(
            inputText = inputText
        )
        Button(
            onClick = {
                onClickCreateBtn(inputText.value)
                afterCompleteCreate()
            }
        ) {
            Text(text = "create")
        }
    }
}