package com.example.islamify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Counter(navController: NavController) {
    var th by remember {
        mutableIntStateOf(0)
    }
    var ten by remember {
        mutableIntStateOf(0)
    }
    var hun by remember {
        mutableIntStateOf(0)
    }
    var un by remember {
        mutableIntStateOf(0)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column(
                modifier=Modifier.width(400.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Button(onClick = {
                    navController.navigate(NavConsts.Menu)
                },
                    Modifier
                        .width(400.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                    ,
                    shape = RoundedCornerShape(
                        0.dp
                    )
                ) {
                    Text(text = "Back", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Cyan)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Spacer(modifier = Modifier.height(20.dp))

            val customFontFamily = FontFamily(
                Font(R.font.f, FontWeight.Bold)
            )

            Text(
                text = "﴾يَا أَيُّهَا الَّذِينَ آمَنُوا﴿",
                style = TextStyle(
                    fontFamily = customFontFamily,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )
            Text(
                text = "﴾اذْكُرُوا اللَّهَ ذِكْرًا كَثِيرًا﴿",
                style = TextStyle(
                    fontFamily = customFontFamily,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(80.dp))

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .width(450.dp)
                    .height(350.dp),
                shape = RoundedCornerShape(16.dp),
            ){

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .width(50.dp)
                                .height(65.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Gray
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = th.toString(), color = Color.White,fontWeight = FontWeight.Bold)
                            }
                        }
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .width(50.dp)
                                .height(65.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = hun.toString(), color = Color.White,fontWeight = FontWeight.Bold)
                            }
                        }
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .width(50.dp)
                                .height(65.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Gray
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = ten.toString(), color = Color.White,fontWeight = FontWeight.Bold)
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .width(50.dp)
                                .height(65.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Gray
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = un.toString(), color = Color.White,fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        modifier = Modifier
                            .height(100.dp)
                            .width(450.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            un++
                            if (un == 10) {
                                un = 0
                                ten++
                                if (ten == 10) {
                                    ten = 0
                                    hun++
                                    if (hun == 10) {
                                        hun = 0
                                        th++
                                    }
                                }
                            }
                        },
                            Modifier
                                .width(150.dp)
                                .height(100.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            )
                            ,
                            shape= RoundedCornerShape(
                                16.dp
                            )
                        ) {
                            Text(text = "+", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.White)
                        }
                        Button(onClick = {
                            if (un > 0 || ten > 0 || hun > 0 || th > 0) {
                                un--
                                if (un < 0) {
                                    un = 9
                                    ten--
                                    if (ten < 0) {
                                        ten = 9
                                        hun--
                                        if (hun < 0) {
                                            hun = 9
                                            th--
                                        }
                                    }
                                }
                            }
                        },
                            Modifier
                                .width(150.dp)
                                .height(100.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            )
                            ,
                            shape= RoundedCornerShape(
                                16.dp
                            )
                        ) {
                            Text(text = "-", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(onClick = {
                        un = 0
                        ten = 0
                        hun = 0
                        th = 0
                    },
                        Modifier
                            .width(300.dp)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        )
                        ,
                        shape= RoundedCornerShape(
                            16.dp
                        )
                    ) {
                        Text(text = "Reset", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
