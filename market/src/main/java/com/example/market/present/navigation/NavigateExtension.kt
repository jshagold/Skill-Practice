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
 * Category 화면으로 이동
 */
fun NavController.navigateToCategory() {
    val navController = this
    this.navigate(Route.CATEGORY) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
    }
}

/**
 * 카테고리 생성 화면으로 이동
 */
fun NavController.navigateToCreateCategory() {
    val navController = this
    navController.navigate(Route.CREATE_CATEGORY)
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

