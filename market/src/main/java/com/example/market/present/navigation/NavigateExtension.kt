package com.example.market.present.navigation

import androidx.navigation.NavController


/**
 *
 * Navigate Extension
 *
 */

/**
 * Home 화면으로 이동
 */
fun NavController.navigateToHome() {
    val navController = this
    this.navigate(Route.HOME) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
    }
}

/**
 * Interest 화면으로 이동
 */
fun NavController.navigateToInterest() {
    val navController = this
    this.navigate(Route.CATEGORY) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
    }
}

/**
 * Basket 화면으로 이동
 */
fun NavController.navigateToBasket() {
    val navController = this
    this.navigate(Route.BASKET) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
    }
}

/**
 * Profile 화면으로 이동
 */
fun NavController.navigateToProfile() {
    val navController = this
    this.navigate(Route.PROFILE) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
    }
}