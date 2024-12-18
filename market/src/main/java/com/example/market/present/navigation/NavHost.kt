package com.example.market.present.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.market.present.ui.home.HomeRoute

@Composable
fun RootNavHost(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Route.HOME
    ) {

        composable(route = Route.HOME) {
            HomeRoute()
        }

        composable(route = Route.BASKET) {  }
        composable(route = Route.INTEREST) {  }
        composable(route = Route.PAYMENT) {  }
        composable(route = Route.PRODUCT) {  }
        composable(route = Route.PROFILE) {  }
        composable(route = Route.SEARCH) {  }
    }
}