package com.example.market.present.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.market.present.navigation.BottomNavItem
import com.example.market.present.navigation.BottomNavHost
import com.example.market.present.navigation.navigateToBasket
import com.example.market.present.navigation.navigateToHome
import com.example.market.present.navigation.navigateToCategory
import com.example.market.present.navigation.navigateToProfile

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        mainNavController = rememberNavController()
    )
}

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController
) {
    MainScreen(
        modifier = modifier,
        mainNavController = mainNavController
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController
) {
    val navController: NavHostController = rememberNavController()
    val currentBackStackEntryState: State<NavBackStackEntry?> = navController.currentBackStackEntryAsState()
    val currentRoute: String? = currentBackStackEntryState.value?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        )   {
            BottomNavHost(
                modifier = Modifier,
                mainNavController = mainNavController,
                navController = navController
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White,
    ) {
        val screens: List<BottomNavItem> = BottomNavItem.getAllNavItem()
        val currentDestination = navController.currentDestination?.route

        screens.forEach {
            NavigationBarItem(
                selected = currentDestination == it.route,
                onClick = {
                    when(it) {
                        BottomNavItem.Basket -> navController.navigateToBasket()
                        BottomNavItem.Home -> navController.navigateToHome()
                        BottomNavItem.Interest -> navController.navigateToCategory()
                        BottomNavItem.Profile -> navController.navigateToProfile()
                    }
                },
                icon = { /*TODO*/ },
                label = {
                    Text(
                        text = stringResource(id = it.text),
                        color = Color.White
                    )
                }
            )
        }

    }
}