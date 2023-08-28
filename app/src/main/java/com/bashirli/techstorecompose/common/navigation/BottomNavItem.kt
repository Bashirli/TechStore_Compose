package com.bashirli.techstorecompose.common.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bashirli.techstorecompose.R

data class BottomNavItem(
    val title:String,
    val route:String,
    val icon:Int
)


fun getBottomNavItems(context:Context):List<BottomNavItem>{
    val resources = context.resources
    val list = listOf<BottomNavItem>(
        BottomNavItem(
            title = resources.getString(R.string.home),
            icon =  R.drawable.home,
            route = Screen.HomeScreen.route
        ),
        BottomNavItem(
            title = resources.getString(R.string.favorites),
            icon = R.drawable.heart,
            route = Screen.FavoritesScreen.route
        ),
        BottomNavItem(
            title = resources.getString(R.string.profile),
            icon =  R.drawable.profile,
            route = Screen.ProfileScreen.route
        ),
        BottomNavItem(
            title = resources.getString(R.string.cart),
            icon = R.drawable.buy,
            route = Screen.CartScreen.route
        ),

        )
    return list
}