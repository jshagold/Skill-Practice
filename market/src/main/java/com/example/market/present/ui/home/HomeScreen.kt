package com.example.market.present.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.present.theme.PastelYellow


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(HomeUiState())
}


@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState
) {

    val scrollState: ScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(PastelYellow)
    ) {
        Text(text = "Home")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    max = (uiState.categoryList.size * 50).dp
                )
        ) {
            items(uiState.categoryList.size) { index ->
                Text(
                    text = uiState.categoryList[index].categoryName,
                    modifier = Modifier
                        .background(Color.Gray)
                )
            }
        }
    }
}