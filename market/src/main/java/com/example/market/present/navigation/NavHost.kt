package com.example.market.present.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.market.present.ui.home.HomeRoute
import com.example.market.present.ui.interest.InterestRoute

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.HOME,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {

        composable(route = Route.HOME) {
            HomeRoute()
        }

        composable(route = Route.BASKET) {  }

        composable(route = Route.INTEREST) {
            InterestRoute()
        }

        composable(route = Route.PAYMENT) {  }
        composable(route = Route.PRODUCT) {  }
        composable(route = Route.PROFILE) {  }
        composable(route = Route.SEARCH) {  }
    }
}