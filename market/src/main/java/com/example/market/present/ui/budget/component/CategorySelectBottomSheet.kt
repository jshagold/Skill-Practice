package com.example.market.present.ui.budget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.market.domain.model.BudgetCategory
import com.example.market.present.ui.shared.component.BottomSheet

@Preview
@Composable
fun PreviewCategorySelectBottomSheet() {
    CategorySelectBottomSheet()
}

@Composable
fun CategorySelectBottomSheet(
    modifier: Modifier = Modifier,
    categoryList: List<BudgetCategory> = listOf(),
    onDismiss: () -> Unit = {},
    onClickCategory: (category: BudgetCategory) -> Unit = {},
) {
    BottomSheet(
        modifier = modifier
            .heightIn(min = 300.dp),
        onDismiss = onDismiss,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 60.dp),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(categoryList.size) { index ->
                CategoryComponent(
                    category = categoryList[index],
                    onClick = {
                        onClickCategory(categoryList[index])
                        onDismiss()
                    }
                )
            }
        }
    }
}