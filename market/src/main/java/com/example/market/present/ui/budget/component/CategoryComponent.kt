package com.example.market.present.ui.budget.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.market.domain.model.BudgetCategory
import com.example.market.present.utils.extension.noRippleClickable

@Preview
@Composable
fun PreviewCategoryComponent() {
    CategoryComponent(
        category = BudgetCategory(
            categoryId = 1,
            categoryName = "월급",
            displayIndex = 1,
        )
    )
}


@Composable
fun CategoryComponent(
    modifier: Modifier = Modifier,
    category: BudgetCategory,
    onClick: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier
            .noRippleClickable {
                onClick()
            }
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(5.dp))
            .padding(5.dp)
    ) {
        val (imageComponent, textComponent) = createRefs()
        Text(
            text = category.categoryName,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(textComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}