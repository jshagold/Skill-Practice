package com.example.market.present.ui.category

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.present.extension.noRippleClickable
import com.example.market.present.theme.PastelYellow
import com.example.market.present.ui.category.component.CategoryTab
import com.example.market.present.ui.category.component.CreateBtn
import com.example.market.present.ui.category.viewmodel.CategoryViewModel
import com.example.market.present.ui.component.EditText

@Preview
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen(
        uiState = CategoryUiState(),
    )
}

@Composable
fun CategoryRoute(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
    navigateToCreateCategory: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CategoryScreen(
        modifier = modifier,
        uiState = uiState,
        deleteCategory = viewModel::deleteCategory,
        navigateToCreateCategory = navigateToCreateCategory,
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    uiState: CategoryUiState,
    deleteCategory: (categoryId: Int) -> Unit = {},
    navigateToCreateCategory: () -> Unit = {},
) {


    val scrollState: ScrollState = rememberScrollState()

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(PastelYellow)
        ) {
            Text(text = "Category")

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(
                        max = (uiState.categoryList.size * 50).dp
                    )
            ) {
                items(uiState.categoryList.size) { index ->
                    CategoryTab(
                        text = uiState.categoryList[index].categoryName,
                        onClickBtn = {
                            deleteCategory(uiState.categoryList[index].categoryId)
                        }
                    )
                }
            }
        }

        CreateBtn(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            onClickBtn = {
                Log.e("TAG", "CategoryScreen: click", )
                navigateToCreateCategory()
            }
        )
    }
}