package com.example.market.present.ui.interest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewInterestScreen() {
    InterestScreen()
}

@Composable
fun InterestRoute() {
    InterestScreen()
}

@Composable
fun InterestScreen() {
    Text(text = "Interest")
}