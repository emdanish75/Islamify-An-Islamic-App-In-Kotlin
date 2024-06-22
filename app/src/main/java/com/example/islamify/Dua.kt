package com.example.islamify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DuaList(duas: List<Dua>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(duas) { dua ->
            DuaItem(dua = dua)
        }
    }
}

@Composable
fun DuaScreen() {
    val duas = listOf(
        Dua(
            title = "Dua for Morning",
            arabicText = "اللّهُمَّ بِكَ أَصْبَحْنَا",
            translation = "O Allah, by You we enter the morning."
        ),
        Dua(
            title = "Dua for Evening",
            arabicText = "اللّهُمَّ بِكَ أَمْسَيْنَا",
            translation = "O Allah, by You we enter the evening."
        ),
        Dua(
            title = "Dua Before Sleeping",
            arabicText = "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
            translation = "In Your name, O Allah, I die and I live."
        ),
        Dua(
            title = "Dua Upon Waking Up",
            arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            translation = "Praise is to Allah Who gives us life after He has caused us to die and to Him is the return."
        ),
        Dua(
            title = "Dua Before Eating",
            arabicText = "بِسْمِ اللَّهِ",
            translation = "In the name of Allah."
        ),
        Dua(
            title = "Dua After Eating",
            arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مُسْلِمِينَ",
            translation = "Praise be to Allah Who has fed us and given us drink and made us Muslims."
        ),
        Dua(
            title = "Dua When Entering the House",
            arabicText = "بِسْمِ اللَّهِ وَلَجْنَا، وَبِسْمِ اللَّهِ خَرَجْنَا، وَعَلَى اللَّهِ رَبِّنَا تَوَكَّلْنَا",
            translation = "In the name of Allah we enter and in the name of Allah we leave, and upon our Lord we rely."
        ),
        Dua(
            title = "Dua When Leaving the House",
            arabicText = "بِسْمِ اللَّهِ، تَوَكَّلْتُ عَلَى اللَّهِ، وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            translation = "In the name of Allah, I place my trust in Allah, and there is no might nor power except with Allah."
        ),
        Dua(
            title = "Dua Before Entering the Toilet",
            arabicText = "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْخُبْثِ وَالْخَبَائِثِ",
            translation = "O Allah, I seek refuge with You from male and female devils."
        ),
        Dua(
            title = "Dua After Leaving the Toilet",
            arabicText = "غُفْرَانَكَ",
            translation = "I ask You for forgiveness."
        ),
        Dua(
            title = "Dua When Starting Ablution",
            arabicText = "بِسْمِ اللَّهِ",
            translation = "In the name of Allah."
        ),
        Dua(
            title = "Dua After Completing Ablution",
            arabicText = "أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ",
            translation = "I bear witness that there is no deity except Allah, alone without any partners, and I bear witness that Muhammad is His servant and His Messenger."
        ),
        Dua(
            title = "Dua for Entering the Mosque",
            arabicText = "اللَّهُمَّ افْتَحْ لِي أَبْوَابَ رَحْمَتِكَ",
            translation = "O Allah, open the doors of Your mercy for me."
        ),
        Dua(
            title = "Dua for Leaving the Mosque",
            arabicText = "اللَّهُمَّ إِنِّي أَسْأَلُكَ مِنْ فَضْلِكَ",
            translation = "O Allah, I ask You from Your favor."
        ),
        Dua(
            title = "Dua for Seeking Guidance",
            arabicText = "اللَّهُمَّ اهْدِنِي وَسَدِّدْنِي",
            translation = "O Allah, guide me and correct me."
        ),
        Dua(
            title = "Dua for Patience",
            arabicText = "رَبِّ اشْرَحْ لِي صَدْرِي وَيَسِّرْ لِي أَمْرِي",
            translation = "O my Lord, expand for me my chest and ease for me my task."
        ),
        Dua(
            title = "Dua for Protection",
            arabicText = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            translation = "In the name of Allah with whose name nothing is harmed on earth nor in the heavens, and He is the All-Hearing, All-Knowing."
        ),
        Dua(
            title = "Dua for Forgiveness",
            arabicText = "رَبِّ اغْفِرْ لِي ذَنْبِي كُلَّهُ",
            translation = "My Lord, forgive me all my sins."
        )
    )
    DuaList(duas = duas)
}


@Composable
fun Dua(navController: NavController) {
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
            DuaScreen()
        }
    }



}
data class Dua(val title: String, val arabicText: String, val translation: String)
@Composable
fun DuaItem(dua: Dua) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp)
                .padding(1.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text(text = dua.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = dua.arabicText, style = MaterialTheme.typography.bodyMedium)
                Text(text = dua.translation, style = MaterialTheme.typography.bodySmall)}

        }
    }
}
