package com.example.islamify

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import java.io.File

data class Para(val number: Int, val title: String)

@Composable
fun Quran(navController: NavController) {
    val paras = (1..30).map { Para(it, "Para $it") }
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column(modifier = Modifier.fillMaxSize()) {
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

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(paras) { para ->
                    ParaItem(para) { openPdf(context, para.number) }
                }
            }
        }
    }

}

@Composable
fun ParaItem(para: Para, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() }
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(18.dp),
                text = para.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

fun openPdf(context: Context, paraNumber: Int) {
    val fileName = "para$paraNumber.pdf"
    val file = File(context.filesDir, fileName)

    context.assets.open(fileName).use { inputStream ->
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
    Log.d("openPdf", "Copied $fileName to internal storage")

    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
    }
}
