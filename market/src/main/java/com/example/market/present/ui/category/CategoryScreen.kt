package com.example.market.present.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.market.present.theme.PastelYellow
import com.example.market.present.ui.component.EditText

@Preview
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen(
        onClickCreateBtn = {}
    )
}

@Composable
fun CategoryRoute(
    viewModel: CategoryViewModel = hiltViewModel()
) {
    CategoryScreen(
        onClickCreateBtn = viewModel::createCategory
    )
}

@Composable
fun CategoryScreen(
    onClickCreateBtn: (categoryName: String) -> Unit,
) {
    val inputText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Category")
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