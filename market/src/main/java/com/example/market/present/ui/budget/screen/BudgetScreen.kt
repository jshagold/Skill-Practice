package com.example.market.present.ui.budget.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.R
import com.example.market.domain.model.Budget
import com.example.market.domain.model.enums.BudgetScreenType
import com.example.market.present.theme.Surface04
import com.example.market.present.theme.Surface07
import com.example.market.present.theme.Surface08
import com.example.market.present.ui.budget.component.BudgetInfo
import com.example.market.present.ui.budget.viewmodel.BudgetViewModel
import com.example.market.present.ui.category.component.CreateBtn


@Preview
@Composable
fun PreviewBudgetScreen() {
    BudgetScreen()
}

@Composable
fun BudgetRoute(
    viewModel: BudgetViewModel = hiltViewModel(),
    navigateToManageBudget: () -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BudgetScreen(
        navigateToManageBudget = navigateToManageBudget
    )
}


@Composable
fun BudgetScreen(
    modifier: Modifier = Modifier,
    navigateToManageBudget: () -> Unit = {},
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Scaffold (
            modifier = Modifier
                .fillMaxSize(),
            contentWindowInsets = WindowInsets(0),
        ) {
            Column (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(White)
            ) {
                Text(
                    text = "2025 01"
                )



                TabRow(
                    containerColor = White,
                    selectedTabIndex = tabIndex,
                    divider = {
                        HorizontalDivider(
                            color = Transparent
                        )
                    },
                    indicator = { tabPositions ->
                        TabRowDefaults.PrimaryIndicator(
                            width = tabPositions[tabIndex].contentWidth,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[tabIndex]),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                ) {
                    BudgetScreenType.entries.forEach { type ->
                        Tab(
                            text = {
                                Text(
                                    text = stringResource(type.stringResource),
                                )
                            },
                            selected = tabIndex == type.index,
                            onClick = { tabIndex = type.index},
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = Surface04,
                        )
                    }
                }


                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = Surface08
                )
                when(tabIndex) {
                    0 -> {
                        BudgetDailyRoute()
                    }
                    1 -> {

                    }
                    2 -> {

                    }
                }
            }
        }

        CreateBtn(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            onClickBtn = {
                navigateToManageBudget()
            }
        )
    }

}