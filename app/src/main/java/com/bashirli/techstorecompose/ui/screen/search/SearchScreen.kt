package com.bashirli.techstorecompose.ui.screen.search

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bashirli.techstorecompose.R
import com.bashirli.techstorecompose.common.base.BaseLazyItem
import com.bashirli.techstorecompose.common.navigation.Screen
import com.bashirli.techstorecompose.common.util.Loading
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.domain.model.products.ProductModel
import com.bashirli.techstorecompose.ui.theme.LightGray
import com.bashirli.techstorecompose.ui.theme.Pink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SearchMVVM = hiltViewModel()
){

    val context = LocalContext.current

    val resources = context.resources

    val focusRequester= remember { FocusRequester() }
    val textFieldLoaded = remember { mutableStateOf(false) }


    val searchText = remember {
        mutableStateOf("")
    }
    
    val state = viewModel.state.value

    LaunchedEffect(key1 = searchText.value){
        delay(300)
        searchQuery(searchText.value,viewModel)

    }


    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier.fillMaxSize().background(color = LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(modifier = modifier.size(50.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                     Icon(
                         painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                         contentDescription = null
                     )
                    }

                OutlinedTextField(
                    modifier = modifier
                        .focusRequester(focusRequester)
                        .onGloballyPositioned {
                            if (!textFieldLoaded.value) {
                                focusRequester.requestFocus()
                                textFieldLoaded.value = !textFieldLoaded.value
                            }
                        }
                        .fillMaxWidth(),
                    value = searchText.value ,
                    onValueChange = {
                        searchText.value=it
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    label = {
                        Text(text = resources.getString(R.string.search))
                    },
                    leadingIcon = {
                       Icon(
                           painter = painterResource(id = R.drawable.search),
                           contentDescription = null
                       )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Pink,
                        focusedBorderColor = Pink,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Pink
                    )
                    )


                }
            Spacer(modifier = modifier.size(50.dp))
            StateCheck(state,navController)
        }
    }
}


@Composable
fun StateCheck(
    state : SearchMVVM.SearchUiState,
    navController:NavHostController
){
    val context = LocalContext.current
    when(state){
        is SearchMVVM.SearchUiState.Error -> {
            Toast.makeText(context,state.message,Toast.LENGTH_LONG).show()
        }

        SearchMVVM.SearchUiState.Loading -> Loading()

        is SearchMVVM.SearchUiState.SearchProducts -> {
            LazySearch(data = state.data, navHostController = navController)
        }

        is SearchMVVM.SearchUiState.InitialProducts->{
            LazySearch(data = state.data, navHostController = navController)
        }

    }
}

@Composable
fun LazySearch(
    data : ProductModel,
    navHostController : NavHostController,
    modifier : Modifier = Modifier,
){

    val context = LocalContext.current
    val resources=context.resources

    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        textAlign = TextAlign.Center,
        text = resources.getString(R.string.found) + " ${data.total} " + resources.getString(R.string.results),
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = Color.Black
    )

    Spacer(modifier = modifier.size(15.dp))


    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)){
       items(data.products){
           BaseLazyItem(product = it, navHostController = navHostController)
       }
    }
}


fun searchQuery(
    query:String,
    viewModel: SearchMVVM
){
    var job : Job?=null
    job = CoroutineScope(Dispatchers.Main).launch {
        if(query.isNotEmpty()){
            viewModel.onEvent(SearchMVVM.SearchEvent.SearchProducts(query))
        }else{
            viewModel.onEvent(SearchMVVM.SearchEvent.InitialProduct)
        }
    }

}