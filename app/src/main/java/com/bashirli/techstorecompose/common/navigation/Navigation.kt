package com.bashirli.techstorecompose.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bashirli.techstorecompose.ui.screen.cart.CartScreen
import com.bashirli.techstorecompose.ui.screen.details.DetailsScreen
import com.bashirli.techstorecompose.ui.screen.favorite.FavoriteScreen
import com.bashirli.techstorecompose.ui.screen.home.HomeScreen
import com.bashirli.techstorecompose.ui.screen.profile.ProfileScreen
import com.bashirli.techstorecompose.ui.screen.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navHostController = navController)
        }
        composable(route = Screen.CartScreen.route){
            CartScreen()
        }
        composable(route = Screen.FavoritesScreen.route){
            FavoriteScreen()
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen()
        }
        composable(
            route = Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.IntType
                    nullable = false
                }
            )
            ){
            it.arguments?.let {
                DetailsScreen(
                    id=it.getInt("id"),
                    navController = navController
                )
            }
        }
    }


}