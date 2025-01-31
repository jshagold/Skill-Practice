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
import com.example.market.present.ui.category.viewmodel.CreateCategoryViewModel
import com.example.market.present.ui.component.EditText

@Composable
fun CreateCategoryRoute(
    viewModel: CreateCategoryViewModel = hiltViewModel()
) {
    CreateCategoryScreen(
        onClickCreateBtn = viewModel::createCategory
    )
}


@Composable
fun CreateCategoryScreen(
    onClickCreateBtn: (categoryName: String) -> Unit = {},
) {
    val inputText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Create Category")
        EditText(
            inputText = inputText
        )
        Button(
            onClick = {
                onClickCreateBtn(inputText.value)
            }
        ) {
            Text(text = "create")
        }
    }
}