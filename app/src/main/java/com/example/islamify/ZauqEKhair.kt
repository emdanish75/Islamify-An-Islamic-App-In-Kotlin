package com.example.islamify

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController

@Composable
fun ZauqeKhair(navController: NavController){
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.width(400.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                navController.navigate(NavConsts.Menu)
                },
                Modifier
                    .width(400.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(
                    0.dp
                )
            ) {
                Text(
                    text = "Back",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Cyan
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Card(
            modifier = Modifier
                .padding(16.dp)
                .width(400.dp)
                .height(400.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier= Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier= Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.zklogo),
                    contentDescription =""
                )
                Text(
                    text = "  Zauq-e-Khair, a beacon of positive   change in our society. Founded on the   principles of kindness and community   service, we strive to make a meaningful   difference through various initiatives   such as donations, community service   projects, and more.\n",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

        }
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.width(400.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {

            },
                Modifier
                    .width(250.dp)
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
                ,
                shape= RoundedCornerShape(
                    16.dp
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            modifier= Modifier.size(120.dp),
                            painter = painterResource(id = R.drawable.zklogo),
                            contentDescription =""
                        )
                        Text(text = "Join Us", color = Color.Black)
                    }

                }
            }
        }

    }
    }
}