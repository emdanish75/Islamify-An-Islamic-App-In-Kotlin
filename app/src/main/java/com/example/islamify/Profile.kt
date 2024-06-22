package com.example.islamify

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Composable
fun Profile(navController: NavController, currentUsername: String) {
    var nameState by remember { mutableStateOf(TextFieldValue()) }
    var usernameState by remember { mutableStateOf(TextFieldValue()) }
    var dobState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }
    var confirmPasswordState by remember { mutableStateOf(TextFieldValue()) }
    var isPassVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(currentUsername) {
        val userDetails = FileUtil.getUserDetails(context, currentUsername)
        if (userDetails != null) {
            nameState = TextFieldValue(userDetails[0])
            usernameState = TextFieldValue(userDetails[1])
            dobState = userDetails[2]
            emailState = TextFieldValue(userDetails[3])
            passwordState = TextFieldValue(userDetails[4])
            confirmPasswordState = TextFieldValue(userDetails[4])
        }
    }

    fun updateUserDetails() {
        val name = nameState.text
        val username = usernameState.text
        val dob = dobState
        val email = emailState.text
        val password = passwordState.text
        val confirmPassword = confirmPasswordState.text

        if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Check email format using regex
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }

        // Continue with update process if all checks pass
        FileUtil.updateUser(context, name, username, dob, email, password)
        Toast.makeText(context, "User Details Updated Successfully", Toast.LENGTH_SHORT).show()
        navController.navigate(NavConsts.Menu)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .height(830.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Hey There! \uD83D\uDE4B\u200D♂\uFE0F")
                    Text(
                        text = "Update Profile",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = nameState,
                        onValueChange = { nameState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Full Name") },
                        trailingIcon = {
                            IconButton(onClick = { nameState = TextFieldValue("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Name Clear"
                                )
                            }
                        }
                    )

                    OutlinedTextField(
                        value = usernameState,
                        onValueChange = { usernameState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Username") },
                        enabled = false
                    )

                    OutlinedTextField(
                        value = dobState,
                        onValueChange = { dobState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        enabled = false,
                        label = { Text(text = "Date of Birth") }
                    )

                    OutlinedTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Email") },
                        trailingIcon = {
                            IconButton(onClick = { emailState = TextFieldValue("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Email Clear"
                                )
                            }
                        }
                    )

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

                    OutlinedTextField(
                        value = confirmPasswordState,
                        onValueChange = { confirmPasswordState = it },
                        visualTransformation = if (isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(text = "Confirm Password") },
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
                        onClick = { updateUserDetails() },
                        Modifier
                            .width(450.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF008577)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Update")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigate(NavConsts.Menu)
                        },
                        Modifier
                            .width(450.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Back")
                    }
                }
            }
        }
    }
}
