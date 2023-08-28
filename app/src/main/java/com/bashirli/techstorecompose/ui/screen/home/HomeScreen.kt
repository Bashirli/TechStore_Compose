package com.bashirli.techstorecompose.ui.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bashirli.techstorecompose.R
import com.bashirli.techstorecompose.common.util.Loading
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.ui.theme.LightGray
import com.bashirli.techstorecompose.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeMVVM = hiltViewModel(),
    navHostController: NavHostController
    ){

    val context = LocalContext.current

    val resources = context.resources

    val searchText = remember {
        mutableStateOf("")
    }

    val state = viewModel.state.value

    val scrollState = rememberScrollState()


    Surface(modifier = modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState),
        color = LightGray
    ) {
        Column(modifier = modifier
            .fillMaxSize()
        ) {
            Spacer(modifier = modifier.size(40.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                ){
                IconButton(onClick = {

                },
                    modifier = modifier
                        .padding(end = 30.dp),
                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = null
                    )
                }

                OutlinedTextField(
                    value = searchText.value,
                    onValueChange = {
                    searchText.value=it
                    },
                    textStyle = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold),
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(30.dp),
                    placeholder = {
                            Text(text = resources.getString(R.string.search))
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            tint = Color.Black
                        )
                                  },
                    label = {
                            Text(text = resources.getString(R.string.search))
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        placeholderColor = Color.Gray,
                        focusedLabelColor = Pink,
                        unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = Pink,
                        unfocusedBorderColor = Color.Gray
                    )
                    )

            }

            Spacer(modifier = modifier.size(55.dp))
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 80.dp),
                text = resources.getString(R.string.orderOnline),
                fontSize = 35.sp,
                lineHeight = 40.sp,
                color = Color.Black,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
                )
            
            Spacer(modifier = modifier.size(50.dp))
            Column(Modifier.fillMaxWidth()) {
                stateCheck(state, context,navHostController)

                TextButton(onClick = {

                },
                    modifier = modifier
                        .align(Alignment.End)
                        .padding(end=10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Pink
                    )
                    ) {
                    Text(text = resources.getString(R.string.seeMore),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,

                        )
                    Spacer(modifier = modifier.size(3.dp))
                    Icon(painter = painterResource(id = R.drawable.vector), contentDescription = null)

                }
            }

        }
    }
}

@Composable
fun stateCheck(
    state:HomeMVVM.HomeState,
    context: Context,
    navHostController: NavHostController
    ){
    when(state){
        is HomeMVVM.HomeState.Categories -> {
            HomeTabLayout(categoryList = state.data, navHostController = navHostController)
        }
        is HomeMVVM.HomeState.Error -> {
            Toast.makeText(context,state.message,Toast.LENGTH_SHORT).show()
        }
        HomeMVVM.HomeState.Loading -> Loading()
        else -> Unit
    }
}