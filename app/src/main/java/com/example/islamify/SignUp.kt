package com.example.islamify

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.material.icons.filled.DateRange
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
import java.util.Calendar

@Composable
fun SignUp(navController: NavController) {
    var nameState by remember { mutableStateOf(TextFieldValue()) }
    var usernameState by remember { mutableStateOf(TextFieldValue()) }
    var dobState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }
    var confirmPasswordState by remember { mutableStateOf(TextFieldValue()) }
    var isPassVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            dobState = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )


    fun signUpUser() {
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

        // Check minimum age requirement
        val dobParts = dob.split("/")
        if (dobParts.size == 3) {
            val year = dobParts[2].toInt()
            val month = dobParts[1].toInt()
            val day = dobParts[0].toInt()
            val dobCalendar = Calendar.getInstance().apply {
                set(year, month - 1, day) // month is 0-based in Calendar
            }
            val todayCalendar = Calendar.getInstance()
            dobCalendar.add(Calendar.YEAR, 5) // Add 5 years to the DOB
            if (dobCalendar.after(todayCalendar)) {
                Toast.makeText(context, "You must be at least 5 years old to sign up", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Check email format using regex
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }

        if (FileUtil.isUserExist(context, username, email)) {
            Toast.makeText(context, "Username or Email already exists", Toast.LENGTH_SHORT).show()
            return
        }

        FileUtil.saveUser(context, name, username, dob, email, password)
        Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show()
        navController.navigate(NavConsts.Login)
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
                        text = "Create an account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = nameState,
                        onValueChange = { nameState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Name") },
                        trailingIcon = {
                            IconButton(onClick = { nameState = TextFieldValue("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Name Clear"
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = usernameState,
                        onValueChange = { usernameState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Username") },
                        trailingIcon = {
                            IconButton(onClick = { usernameState = TextFieldValue("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Username Clear"
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = dobState,
                        onValueChange = { dobState = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "D.O.B") },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { dobState = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "D.O.B Clear"
                                )
                            }
                        },
                        leadingIcon = {
                            IconButton(onClick = { datePickerDialog.show() }) {
                                Icon(
                                    imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Select D.O.B"
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))

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
                    Spacer(modifier = Modifier.height(5.dp))

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
                    Spacer(modifier = Modifier.height(5.dp))

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
                        onClick = { signUpUser() },
                        Modifier
                            .width(450.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Sign Up")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigate(NavConsts.Login)
                        },
                        Modifier
                            .width(450.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}

