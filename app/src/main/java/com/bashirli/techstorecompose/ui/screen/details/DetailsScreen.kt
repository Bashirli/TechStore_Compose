package com.bashirli.techstorecompose.ui.screen.details

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.bashirli.techstorecompose.R
import com.bashirli.techstorecompose.common.util.Loading
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.ui.theme.Green
import com.bashirli.techstorecompose.ui.theme.Pink
import com.bashirli.techstorecompose.ui.theme.RoseGold
import com.bashirli.techstorecompose.ui.theme.SkyBlue

@Composable
fun DetailsScreen(
    navController: NavHostController,
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailsMVVM = hiltViewModel()
){

    val state = viewModel.state.value

    val isCalled = remember { mutableStateOf(false) }

    if(!isCalled.value){
        viewModel.onEvent(DetailsMVVM.DetailsEvent.GetProductDetails(id))
        isCalled.value=!isCalled.value
    }

        Surface(
            modifier = modifier
                .background(Color.LightGray)
                .fillMaxSize()
            ) {
                    DetailsState(state = state, navController = navController)
        }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailsState(
    state : DetailsMVVM.DetailsUiState,
    navController: NavHostController
){
    val context = LocalContext.current

        when(state){
            is DetailsMVVM.DetailsUiState.Error -> {
                Toast.makeText(context,state.message,Toast.LENGTH_LONG).show()
            }
            DetailsMVVM.DetailsUiState.Loading -> {
                Loading()
            }
            is DetailsMVVM.DetailsUiState.ProductDetails -> {

                   ProductDetails(navController = navController,data = state.data)
                }
            }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(
    navController: NavHostController,
    data : ProductListModel,
    modifier: Modifier = Modifier
){

    val scrollState = rememberScrollState()

    val context  = LocalContext.current

    val resources = context.resources


    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        AsyncImage(
            model = data.images[0],
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(390.dp)
            )

      Column(modifier = modifier.fillMaxSize()) {
          Row(
              modifier = modifier
                  .fillMaxWidth()
                  .padding(horizontal = 30.dp, vertical = 30.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
          ) {
              IconButton(onClick = {
                  navController.popBackStack()
              }) {
                  Icon(
                      painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                      contentDescription = null
                  )
              }
              IconButton(onClick = { /*TODO*/ }) {
                  Icon(
                      painter = painterResource(id = R.drawable.heart),
                      contentDescription = null
                  )
              }
          }

          Column(modifier = modifier
              .verticalScroll(state = scrollState)
              .fillMaxWidth()

          ){

              Spacer(modifier = modifier.size(305.dp))
              Card(
                  modifier = modifier
                      .fillMaxWidth(),
                  elevation = CardDefaults.cardElevation(
                      defaultElevation = 5.dp
                  ),
                  shape = RoundedCornerShape(
                      topEnd = 17.dp,
                      topStart = 17.dp,
                      bottomEnd = 0.dp,
                      bottomStart = 0.dp
                  ),
                  colors = CardDefaults.cardColors(
                      containerColor = Color.White
                  )
              ) {
                  Column(
                      modifier  = modifier.padding(horizontal = 40.dp),
                  ) {
                      Spacer(modifier = modifier.size(28.dp))
                      Text( text  = data.title ?: "" ,
                          fontSize = 28.sp,
                          color = Color.Black,
                          fontFamily = fontFamily,
                          fontWeight = FontWeight.Bold,
                          textAlign = TextAlign.Center
                      )
                      Text(
                          text = resources.getString(R.string.colors),
                          fontSize = 17.sp,
                          fontFamily = fontFamily,
                          fontWeight = FontWeight.Bold,
                          color = Color.Black
                      )
                    
                      
                      Spacer(modifier = modifier.size(10.dp))
                      Row(
                          modifier = modifier.fillMaxWidth(),
                          
                      ) {
                          SuggestionChip(
                              onClick = {

                              },
                              label = {
                                  Text(text = resources.getString(R.string.skyBlue),
                                      fontFamily = fontFamily,
                                      fontWeight = FontWeight.Bold,
                                      fontSize = 12.sp,
                                      color = Color.Black)
                              },
                              icon = {
                                  Icon(painter = painterResource(id = R.drawable.purple),
                                      contentDescription = null,
                                      tint = SkyBlue

                                      )
                              },
                              modifier = modifier.padding(horizontal = 5.dp)
                          )

                          SuggestionChip(
                              onClick = {

                              },
                              label = {
                                  Text(text = resources.getString(R.string.roseGold),
                                      fontFamily = fontFamily,
                                      fontWeight = FontWeight.Bold,
                                      fontSize = 12.sp,
                                      color = Color.Black)
                              },
                              icon = {
                                  Icon(painter = painterResource(id = R.drawable.rose_gold),
                                      contentDescription = null,
                                      tint = RoseGold
                                      )
                              },
                              modifier = modifier.padding(horizontal = 5.dp)
                          )

                          SuggestionChip(
                              onClick = {

                              },
                              label = {
                                  Text(text = resources.getString(R.string.green),
                                      fontFamily = fontFamily,
                                      fontWeight = FontWeight.Bold,
                                      fontSize = 12.sp,
                                      color = Color.Black
                                      )
                              },
                              icon = {
                                  Icon(
                                      painter = painterResource(id = R.drawable.green),
                                      contentDescription = null,
                                        tint = Green
                                      )
                              },
                              modifier = modifier.padding(horizontal = 5.dp)
                          )




                      }

                      Spacer(modifier = modifier.size(40.dp))

                      Text(
                          text = data.description ?: "",
                          fontFamily = fontFamily,
                          fontWeight = FontWeight.Medium,
                          color = Color.Gray,
                          fontSize = 15.sp
                          )

                      Spacer(modifier = modifier.size(10.dp))

                      TextButton(
                          onClick = { /*TODO*/ },
                          ) {
                          Row(modifier = modifier,
                              verticalAlignment = Alignment.CenterVertically
                              ) {
                              Text(text = resources.getString(R.string.fullView),
                                  fontFamily = fontFamily,
                                  fontWeight = FontWeight.Bold,
                                  color = Pink,
                                  fontSize = 15.sp,
                                  modifier = modifier.padding(horizontal = 5.dp)

                              )
                              Icon(painter = painterResource(id = R.drawable.vector),
                                  contentDescription = null,
                                  tint = Pink
                              )
                          }
                      }
                      
                      Spacer(modifier = modifier.size(30.dp))
                      
                      Row(modifier = modifier.fillMaxWidth(),
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                          ) {
                          Text(text = resources.getString(R.string.total),
                              fontFamily= fontFamily,
                              fontWeight = FontWeight.Normal,
                              fontSize = 17.sp,
                              color = Color.Black

                              )
                          Text(text = "$${data.price?.toString()}",
                              fontSize = 17.sp,
                              fontWeight = FontWeight.Bold,
                              fontFamily = fontFamily,
                              color = Pink
                              )
                      }

                      Spacer(modifier = modifier.size(32.dp))
                      FilledTonalButton(
                          modifier = modifier.fillMaxWidth(),
                          onClick = {},
                          shape = RoundedCornerShape(size = 10.dp),
                          colors = ButtonDefaults.filledTonalButtonColors(
                              containerColor = Pink
                          )
                          ) {
                          Text(text = resources.getString(R.string.addToBasket),
                              fontFamily = fontFamily,
                              modifier = modifier.padding(vertical = 10.dp),
                              fontWeight = FontWeight.Bold,
                              color = Color.White,
                              fontSize = 20.sp
                              )
                      }
                      Spacer(modifier = modifier.size(50.dp))

                  }
              }
          }
      }

    }
}

