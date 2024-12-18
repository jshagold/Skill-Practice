package com.example.market.present.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.market.present.navigation.RootNavHost

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

@Composable
fun MainRoute() {
    MainScreen()
}

@Composable
fun MainScreen() {

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        )   {
            RootNavHost()
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White,
    ) {

    }
}