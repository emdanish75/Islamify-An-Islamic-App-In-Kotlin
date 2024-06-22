package com.example.islamify

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.islamify.ui.theme.IslamifyTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private lateinit var qiblaSensor: QiblaSensor
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        qiblaSensor = QiblaSensor(this)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = NavConsts.Login) {
                composable(NavConsts.Login) { Login(navController) }
                composable(NavConsts.SignUp) { SignUp(navController) }
                composable(NavConsts.Menu) {
                    Menu(navController)
                }
                composable(NavConsts.Quran) { Quran(navController) }
                composable(NavConsts.Qibla) { Qibla(navController) }
                composable(NavConsts.Dua) { Dua(navController) }
                composable(NavConsts.Tasbih) { Counter(navController) }
                composable(NavConsts.Zauq_e_Khair) { ZauqeKhair(navController) }
                composable(NavConsts.ForgotPassword) { ForgotPassword(navController) }
                composable(
                    route = "${NavConsts.Profile}/{username}",
                    arguments = listOf(navArgument("username") { type = NavType.StringType })
                ) { backStackEntry ->
                    val username = backStackEntry.arguments?.getString("username",GlobalData.username)
                    username?.let {
                        Profile(navController, it)
                    }
                }
            }
        }


        getLocation()
    }

    private fun getLocation() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        qiblaSensor.updateLocation(latitude, longitude)
                    }
                }
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Toast.makeText(this, "Location permission is needed to show your location.", Toast.LENGTH_LONG).show()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            getLocation()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IslamifyTheme {
        // UI preview
    }
}


object NavConsts {
    const val Menu = "Menu"
    const val Quran = "Quran"
    const val Qibla = "Qibla"
    const val Dua = "Dua"
    const val Tasbih = "Tasbih"
    const val SignUp = "Sign Up"
    const val Login = "Login"
    const val Zauq_e_Khair = "Zauq-e-Khair"
    const val ForgotPassword = "Forgot Password"
    const val Profile = "Profile"
}
