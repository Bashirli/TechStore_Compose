package com.bashirli.techstorecompose.common.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bashirli.techstorecompose.common.navigation.Screen
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.ui.theme.Pink


@Composable
fun BaseLazyItem(
    product : ProductListModel,
    navHostController :NavHostController,
    modifier : Modifier = Modifier,
){
    Box(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable {
                product.id?.let {
                    navHostController.navigate(Screen.DetailsScreen.withArgs(it))
                }
            },
        contentAlignment = Alignment.TopCenter,
    ){
        Card(
            modifier = modifier
                .padding(top = 50.dp)
                .width(220.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Spacer(modifier = modifier.size(145.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = product.title?:"",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = ("$" + product.price?.toString()) ?: "",
                color = Pink,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.size(40.dp))
        }

        Card(
            modifier = modifier
                .size(160.dp)

            ,
            shape = RoundedCornerShape(80.dp)
        ) {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                    modifier= modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                    model = product.images.first(),
                    contentDescription = null
                )
            }
        }
    }
}