package com.example.market.present.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.market.present.ui.MainRoute
import com.example.market.present.ui.basket.BasketRoute
import com.example.market.present.ui.home.HomeRoute
import com.example.market.present.ui.category.CategoryRoute
import com.example.market.present.ui.category.CreateCategoryRoute
import com.example.market.present.ui.profile.ProfileRoute


/**
 * 전체 화면 NavHost
 */
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.MAIN,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable(route = Route.MAIN) {
            MainRoute(
                mainNavController = navController
            )
        }


        composable(route = Route.CREATE_CATEGORY) {
            CreateCategoryRoute()
        }
    }
}



/**
 * Bottom Navigation Tab 화면 NavHost
 */
@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
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
            HomeRoute(
                modifier = modifier
            )
        }

        composable(route = Route.BASKET) {
            BasketRoute()
        }

        composable(route = Route.CATEGORY) {
            CategoryRoute(
                modifier = Modifier,
                navigateToCreateCategory = {
                    mainNavController.navigateToCreateCategory()
                }
            )
        }

        composable(route = Route.PROFILE) {
            ProfileRoute()
        }

        composable(route = Route.PAYMENT) {  }
        composable(route = Route.PRODUCT) {  }
        composable(route = Route.SEARCH) {  }
    }
}