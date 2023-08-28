package com.bashirli.techstorecompose.common.navigation

sealed class Screen(val route :String) {

    object SplashScreen: Screen("splashScreen")

    object HomeScreen : Screen("homeScreen")

    object CartScreen : Screen("cartScreen")

    object FavoritesScreen: Screen("FavoritesScreen")

    object ProfileScreen : Screen("ProfileScreen")

    object DetailsScreen : Screen("DetailsScreen")

    fun withArgs(vararg args:Int):String{
        return buildString{
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }


}