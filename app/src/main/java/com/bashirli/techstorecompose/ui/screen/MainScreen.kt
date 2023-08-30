package com.bashirli.techstorecompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bashirli.techstorecompose.common.navigation.BottomNavigationBar
import com.bashirli.techstorecompose.common.navigation.Navigation
import com.bashirli.techstorecompose.common.navigation.Screen
import com.bashirli.techstorecompose.common.navigation.getBottomNavItems
import com.bashirli.techstorecompose.ui.theme.LightGray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    val navController = rememberNavController()

    val bottomBarState = remember {
        mutableStateOf(false)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    bottomBarState.value = changeVisibility(navBackStackEntry)


    Scaffold(
    bottomBar = {
            if(bottomBarState.value){
                BottomNavigationBar(
                    items = getBottomNavItems(context),
                    navController = navController,
                )
            }
    },
    ) {

        Box(modifier = androidx.compose.ui.Modifier.padding(it)
            ){
            Navigation(navController = navController)
        }
    }

}

fun changeVisibility(navBackStackEntry: NavBackStackEntry?) : Boolean{
   return  when(navBackStackEntry?.destination?.route){
       Screen.SplashScreen.route->{
           false
       }
       Screen.DetailsScreen.route + "/{id}"->{
           false
       }
       Screen.SearchScreen.route->{
           false
       }
       else->{
           true
       }
    }
}