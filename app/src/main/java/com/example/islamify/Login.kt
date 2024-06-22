
package com.example.islamify

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel




@Composable
fun Login(navController: NavController) {
    var usernameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var isPassVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(300.dp)
                    .width(500.dp),
                painter = painterResource(id = R.drawable.mosque),
                contentDescription = "login"
            )
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .height(500.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Welcome Back!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = usernameState,
                        onValueChange = { usernameState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Username") },
                        trailingIcon = {
                            IconButton(onClick = { usernameState = "" }) {
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Username Clear")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        visualTransformation = if (isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(text = "Password") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = { isPassVisible = !isPassVisible }) {
                                Icon(
                                    imageVector = if (isPassVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (isPassVisible) "Hide Password" else "Show Password"
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            val enteredUsername = usernameState.trim()
                            val enteredPassword = passwordState

                            if (enteredUsername.isNotEmpty() && enteredPassword.isNotEmpty()) {
                                val storedPassword = FileUtil.getUserPassword(context, enteredUsername)
                                if (storedPassword != null && enteredPassword == storedPassword) {
                                    GlobalData.username=enteredUsername
                                    navController.navigate(NavConsts.Menu)
                                } else {
                                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Modifier.fillMaxWidth(),
                        enabled = usernameState.isNotBlank() && passwordState.isNotBlank()
                    ) {
                        Text("Login")
                    }
                    Spacer(modifier = Modifier.weight(2f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = {
                            navController.navigate(NavConsts.SignUp)
                        }) {
                            Text(text = "Sign Up")
                        }
                        TextButton(onClick = {
                            navController.navigate(NavConsts.ForgotPassword)
                        }) {
                            Text(text = "Forgot Password?", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ForgotPassword(navController: NavController) {
    var emailState by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Forgot Password",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Email") },
                        trailingIcon = {
                            IconButton(onClick = { emailState = "" }) {
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Email Clear")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            val enteredEmail = emailState.trim()

                            if (enteredEmail.isNotEmpty()) {
                                val storedPassword = FileUtil.getUserPasswordByEmail(context, enteredEmail)
                                if (storedPassword != null) {
                                    Toast.makeText(context, "Your password is: $storedPassword", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(context, "Email not found", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Modifier.fillMaxWidth(),
                        enabled = emailState.isNotBlank()
                    ) {
                        Text("Retrieve Password")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    TextButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Text(text = "Back to Login")
                    }
                }
            }
        }
    }
}
