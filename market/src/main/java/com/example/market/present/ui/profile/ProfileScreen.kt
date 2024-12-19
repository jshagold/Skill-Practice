package com.example.market.present.ui.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}

@Composable
fun ProfileRoute() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Text(text = "Profile")
}