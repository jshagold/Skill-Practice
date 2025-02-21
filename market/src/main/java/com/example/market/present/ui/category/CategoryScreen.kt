package com.example.market.present.ui.category

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.R
import com.example.market.present.theme.PastelYellow
import com.example.market.present.theme.PrimaryGreen
import com.example.market.present.theme.PrimaryLightBlue
import com.example.market.present.ui.budget.component.dragdrop.DragDropList
import com.example.market.present.ui.category.component.CategoryControlTab
import com.example.market.present.ui.category.component.CreateBtn
import com.example.market.present.ui.category.viewmodel.CategoryViewModel
import com.example.market.present.utils.extension.noRippleClickable

@Preview
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen(
        uiState = CategoryUiState(),
        changeListIndex = {_,_->},
        saveCategoryListIndex = {}
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
        changeListIndex = viewModel::moveListIndex,
        saveCategoryListIndex = viewModel::saveListIndex,
        navigateToCreateCategory = navigateToCreateCategory,
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    uiState: CategoryUiState,
    deleteCategory: (categoryId: Int) -> Unit = {},
    changeListIndex: (from: Int, to: Int) -> Unit,
    saveCategoryListIndex: () -> Unit,
    navigateToCreateCategory: () -> Unit = {},
) {
    var isSet by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PastelYellow)
        ) {
            Text(text = "Category")

            DragDropList(
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp
                    ),
                listSize = uiState.categoryList.size,
                changeListIndex = changeListIndex,
                isActivate = isSet
            ) { modifier, index ->
                CategoryControlTab(
                    modifier = modifier,
                    text = uiState.categoryList[index].categoryName,
                    isSet = isSet,
                    onClickBtn = {
                        deleteCategory(uiState.categoryList[index].categoryId)
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = modifier
                    .background(color = PrimaryLightBlue, shape = CircleShape)
                    .size(75.dp)
                    .noRippleClickable {
                    }
            ) {
                if(isSet) {
                    Text(
                        text = "적용",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .noRippleClickable {
                                saveCategoryListIndex()
                                isSet = !isSet
                            }
                    )
                } else {
                    Text(
                        text = "수정",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .noRippleClickable {
                                isSet = !isSet
                            }
                    )
                }
            }

            CreateBtn(
                onClickBtn = {
                    Log.e("TAG", "CategoryScreen: click", )
                    navigateToCreateCategory()
                }
            )
        }
    }
}