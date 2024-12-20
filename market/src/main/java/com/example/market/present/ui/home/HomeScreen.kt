package com.example.market.present.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


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
    Text(text = "Home")
}