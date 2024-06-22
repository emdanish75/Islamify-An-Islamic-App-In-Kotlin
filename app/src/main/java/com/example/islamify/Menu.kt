package com.example.islamify

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController) {
    val username = GlobalData.username ?: ""
    val context = LocalContext.current
    Text(text = "Welcome, $username!")


    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .width(400.dp)
                    .height(45.dp),
                shape = RoundedCornerShape(16.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {
                        navController.navigate("${NavConsts.Profile}/${username}")
                    })
                    {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(NavConsts.Login)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "logout"
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .width(400.dp)
                    .height(220.dp),
                shape = RoundedCornerShape(16.dp)
            ){
                Row(
                    modifier = Modifier
                        .height(220.dp)
                        .width(400.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Fajr",
                            style = TextStyle(
                                fontSize = 50.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )    )
                        Text(
                            text = "3:23 AM",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Gray
                            )    )
                    }
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(220.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Zuhr",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )    )
                        Text(
                            text = "12:07 PM",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Gray
                            )    )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Asr",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )    )
                        Text(
                            text = "5:05 PM",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Gray
                            )    )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Maghrib",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )    )
                        Text(
                            text = "7:13 PM",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Gray
                            )    )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Isha",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )    )
                        Text(
                            text = "8:53 PM",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Gray
                            )    )
                    }
                }
            }

            Text(text = "Explore", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(10.dp))

            Row (modifier = Modifier
                .height(250.dp)
                .width(400.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top)
            {

                Column {
                    Button(onClick = {
                        navController.navigate(NavConsts.Qibla)

                    },
                        Modifier
                            .width(150.dp)
                            .height(100.dp),
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
                            Image(
                                modifier= Modifier.size(50.dp),
                                painter = painterResource(id = R.drawable.qibla),
                                contentDescription =""
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Qibla", color = Color.Black)
                        }

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        navController.navigate(NavConsts.Quran)
                    },
                        Modifier
                            .width(150.dp)
                            .height(100.dp),
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
                            Image(
                                modifier= Modifier.size(50.dp),
                                painter = painterResource(id = R.drawable.recite),
                                contentDescription =""
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Quran", color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }
                Spacer(modifier = Modifier.width(25.dp))

                Column {
                    Button(onClick = {
                        navController.navigate(NavConsts.Tasbih)
                    },
                        Modifier
                            .width(150.dp)
                            .height(100.dp),
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
                            Image(
                                modifier= Modifier.size(50.dp),
                                painter = painterResource(id = R.drawable.tasbih),
                                contentDescription =""
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Tasbih", color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        navController.navigate(NavConsts.Dua)
                    },
                        Modifier
                            .width(150.dp)
                            .height(100.dp),
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
                            Image(
                                modifier= Modifier.size(50.dp),
                                painter = painterResource(id = R.drawable.prayer),
                                contentDescription =""
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Dua", color = Color.Black)
                        }
                    }

                }

            }
            Button(onClick = {
                navController.navigate(NavConsts.Zauq_e_Khair)

            },
                Modifier
                    .width(320.dp)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
                ,
                shape= RoundedCornerShape(
                    16.dp
                )
            ) {
                Column {
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            modifier= Modifier.size(80.dp),
                            painter = painterResource(id = R.drawable.zklogo),
                            contentDescription =""
                        )
                        Text(text = "Zauq e Khair", color = Color.Black)
                    }
                }

            }

        }
    }
}
