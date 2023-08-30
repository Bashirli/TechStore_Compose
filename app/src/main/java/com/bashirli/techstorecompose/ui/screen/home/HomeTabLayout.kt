package com.bashirli.techstorecompose.ui.screen.home

import android.util.Log
import android.widget.Space
import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab

import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bashirli.techstorecompose.R
import com.bashirli.techstorecompose.common.base.BaseLazyItem
import com.bashirli.techstorecompose.common.navigation.Screen
import com.bashirli.techstorecompose.common.util.Loading
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.domain.model.CategoryModel
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.ui.screen.cart.CartScreen
import com.bashirli.techstorecompose.ui.screen.favorite.FavoriteScreen
import com.bashirli.techstorecompose.ui.screen.profile.ProfileScreen
import com.bashirli.techstorecompose.ui.theme.LightGray
import com.bashirli.techstorecompose.ui.theme.Pink

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabLayout(
    modifier: Modifier = Modifier,
    categoryList: CategoryModel,
    viewModel: HomeMVVM = hiltViewModel(),
    navHostController: NavHostController

    ){
    val tabIndex = remember{ mutableStateOf(0) }

    val state = viewModel.categoryState.value

    val initialCall = remember{ mutableStateOf(true) }

    Column(modifier = modifier.fillMaxWidth()) {

       if(initialCall.value){
            viewModel.onEvent(HomeMVVM.HomeEvent.CategoryProduct(categoryList.list.first()))
            initialCall.value=!initialCall.value
        }



            ScrollableTabRow(

                selectedTabIndex = tabIndex.value,
                contentColor = Pink,
                containerColor = LightGray,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = modifier.tabIndicatorOffset(it[tabIndex.value])
                        ,
                        height = 4.dp,
                        color = Pink
                    )
                },
                divider = {}
            ) {

                categoryList.list.forEachIndexed {index,title->
                    Tab(
                        selected = tabIndex.value == index,
                        onClick = {
                            tabIndex.value=index
                            viewModel.onEvent(HomeMVVM.HomeEvent.CategoryProduct(title))
                        },
                        text = {
                            Text(
                                text = title,
                                color = Pink,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }

        Spacer(modifier = modifier.size(15.dp))

        StateCheck(state = state, navHostController = navHostController)

    }

}

@Composable
fun StateCheck(
    state:HomeMVVM.HomeCategoriesState,
    navHostController: NavHostController
){
    val context = LocalContext.current

    when(state){
        is HomeMVVM.HomeCategoriesState.CategoryProducts -> {
            CategoryItems(data = state.data.products, navHostController = navHostController)
        }
        is HomeMVVM.HomeCategoriesState.Error -> {
            Toast.makeText(context,state.message,Toast.LENGTH_SHORT).show()
        }
        is HomeMVVM.HomeCategoriesState.LoadingCategories-> Loading()
        else -> Unit
    }
}

@Composable
fun CategoryItems(
    data : List<ProductListModel>,
    modifier : Modifier=Modifier,
    navHostController: NavHostController
){

    LazyRow(){

            items(data){
                BaseLazyItem(product = it, navHostController = navHostController)
            }

    }

}