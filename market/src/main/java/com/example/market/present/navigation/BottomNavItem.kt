package com.example.market.present.navigation

import com.example.market.R

sealed class BottomNavItem(val route: String, val text: Int) {
    data object Home : BottomNavItem(route = Route.HOME, text = R.string.bottom_nav_home)

    data object Interest : BottomNavItem(route = Route.CATEGORY, text = R.string.bottom_nav_category)

    data object Profile : BottomNavItem(route = Route.PROFILE, text = R.string.bottom_nav_profile)

    data object Basket : BottomNavItem(route = Route.BASKET, text = R.string.bottom_nav_basket)



    companion object {
        fun getAllNavItem(): List<BottomNavItem> {
            return listOf(
                Home,
                Interest,
                Profile,
                Basket
            )
        }
    }
}