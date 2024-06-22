package com.example.islamify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Qibla(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
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
            QiblaCompass()
        }
    }
}

@Composable
fun QiblaCompass() {
    val context = LocalContext.current
    val qiblaSensor = remember { QiblaSensor(context) }

    Box(modifier = Modifier.fillMaxSize()) {


        val needlePainter = painterResource(id = R.drawable.qiblacompass)
        Box(modifier = Modifier.fillMaxSize()) {
            val rotationAngle = qiblaSensor.qiblaDirection.value
            Image(
                painter = needlePainter,
                contentDescription = "Qibla Needle",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(rotationAngle+180)
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            qiblaSensor.unregister()
        }
    }
}
