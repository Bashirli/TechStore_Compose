package com.bashirli.techstorecompose.common.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.ui.theme.Pink

@Composable
fun BottomNavigationBar(
    items:List<BottomNavItem>,
    navController: NavController,
    modifier : Modifier = Modifier
){


       NavigationBar(
           modifier = modifier,
           contentColor = Color.White
       ) {
           val backStackEntry = navController.currentBackStackEntryAsState()


           items.forEach {
               val selected = it.route==backStackEntry.value?.destination?.route
               NavigationBarItem(selected = selected,
                   onClick = {
                       navController.navigate(it.route)
                   },
                   icon = {
                           Icon(painter = painterResource(id = it.icon),contentDescription = null)
                   },
                   colors = NavigationBarItemDefaults.colors(
                       selectedTextColor = Pink,
                       selectedIconColor = Pink,
                       unselectedIconColor = Color.Gray,
                       unselectedTextColor = Color.Gray,
                       indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)

                       )
               )

           }
       }

   }



