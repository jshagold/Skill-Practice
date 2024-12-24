package com.example.market.present.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.market.present.theme.PastelYellow


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}


@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {

    val scrollState: ScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(PastelYellow)
    ) {
        Text(text = "Home")
    }
}