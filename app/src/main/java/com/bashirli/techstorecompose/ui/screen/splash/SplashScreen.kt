package com.bashirli.techstorecompose.ui.screen.splash

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bashirli.techstorecompose.R
import com.bashirli.techstorecompose.common.navigation.Screen
import com.bashirli.techstorecompose.common.util.fontFamily
import com.bashirli.techstorecompose.ui.theme.Pink

@Composable
fun SplashScreen(
    modifier : Modifier = Modifier,
    navController: NavHostController
){

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val resources = context.resources

    Surface(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier
            .fillMaxSize()
            .background(color = Pink)
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            Spacer(modifier = modifier.height(10.dp))
                Column {
                    Text(
                        modifier = modifier
                            .padding(horizontal = 50.dp)
                        ,
                        text = resources.getString(R.string.splashText),
                        fontSize =50.sp,
                        color = Color.White,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        modifier = modifier
                            .padding(horizontal = 50.dp)
                        ,
                        text = resources.getString(R.string.gadget),
                        fontSize =50.sp,
                        color = Color.White,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                    )
                }


            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    ,
                painter = painterResource(id = R.drawable.splashimage),
                contentDescription = null
            )
            FilledTonalButton(onClick = {
                navController.navigate(Screen.HomeScreen.route){
                    popUpTo(Screen.SplashScreen.route){
                        inclusive=true
                    }
                }
            },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(vertical = 30.dp)
                    ,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                )
                ) {
                Text(
                    modifier = modifier.padding(vertical = 20.dp),
                    text = resources.getString(R.string.getStarted),
                    color = Pink,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    )
            }

        }
    }
}
