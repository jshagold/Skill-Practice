package com.example.market.present.ui.budget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.market.R
import com.example.market.domain.model.BudgetCategory


@Preview
@Composable
fun PreviewCategoryTab() {
    CategoryTab()
}

@Composable
fun CategoryTab(
    modifier: Modifier = Modifier,
    mainColor: Color = MaterialTheme.colorScheme.primary,
    tabContents: () -> Unit = {},
    selectedCategory: List<BudgetCategory?> = listOf(),
    onFocus: Boolean = false,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (labelComponent, contentsComponent) = createRefs()
        val labelGuideLine = createGuidelineFromStart(fraction = 0.2f)

        Text(
            text = stringResource(R.string.category_tab_label),
            modifier = Modifier
                .padding(vertical = 10.dp)
                .constrainAs(labelComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(labelGuideLine)
                    width = Dimension.fillToConstraints
                }
        )

        ConstraintLayout(
            modifier = Modifier
                .constrainAs(contentsComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(labelGuideLine)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            awaitPointerEvent(PointerEventPass.Initial)
                            tabContents()
                        }
                    }
                }
        ) {
            val (bottomDivider, categoryComponent) = createRefs()

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .constrainAs(categoryComponent) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ) {
                selectedCategory.forEach { category ->
                    if(category != null) {
                        CategoryComponent(
                            mainColor = mainColor,
                            category = category,
                            modifier = Modifier
                        )
                    }
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = if(onFocus) mainColor else MaterialTheme.colorScheme.surfaceDim,
                modifier = Modifier
                    .constrainAs(bottomDivider) {
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}