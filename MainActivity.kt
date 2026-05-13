package com.example.nammamistri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NammaMistriApp()
        }
    }
}

@Composable
fun NammaMistriApp() {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var currentScreen by remember { mutableStateOf("login") }

    val laborRecords = remember { mutableStateListOf<String>() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE8F5E9)
    ) {

        when (currentScreen) {

            // LOGIN SCREEN
            "login" -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Namma Mistri",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        shape = RoundedCornerShape(15.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(15.dp)
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        onClick = {

                            if (username == "admin" && password == "1234") {

                                currentScreen = "home"
                                errorMessage = ""

                            } else {

                                errorMessage = "Invalid username or password"
                            }
                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D32)
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text(
                            text = "Login",
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (errorMessage.isNotEmpty()) {

                        Text(
                            text = errorMessage,
                            color = Color.Red
                        )
                    }
                }
            }

            // HOME SCREEN
            "home" -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Welcome to Namma Mistri",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = { currentScreen = "calculator" },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF388E3C)
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text(
                            text = "Material Calculator",
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { currentScreen = "labor" },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF388E3C)
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text(
                            text = "Add Labor Details",
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { currentScreen = "records" },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF388E3C)
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text(
                            text = "View Records",
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // MATERIAL CALCULATOR
            "calculator" -> {

                var bricks by remember { mutableStateOf("") }
                var result by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Material Calculator",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = bricks,
                        onValueChange = { bricks = it },
                        label = { Text("Number of Bricks") },
                        shape = RoundedCornerShape(15.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                            val brickCount = bricks.toIntOrNull() ?: 0

                            val cement = brickCount / 50
                            val sand = brickCount * 2

                            result =
                                "Cement Bags: $cement\nSand: $sand kg"
                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D32)
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text("Calculate")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (result.isNotEmpty()) {

                        Text(
                            text = result,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        onClick = { currentScreen = "home" },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text("Back")
                    }
                }
            }

            // LABOR DETAILS
            "labor" -> {

                var laborName by remember { mutableStateOf("") }
                var wage by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Add Labor Details",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = laborName,
                        onValueChange = { laborName = it },
                        label = { Text("Labor Name") },
                        shape = RoundedCornerShape(15.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = wage,
                        onValueChange = { wage = it },
                        label = { Text("Daily Wage") },
                        shape = RoundedCornerShape(15.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                            if (laborName.isNotEmpty() && wage.isNotEmpty()) {

                                laborRecords.add("$laborName - ₹$wage")

                                laborName = ""
                                wage = ""
                            }
                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D32)
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text("Save")
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        onClick = { currentScreen = "home" },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text("Back")
                    }
                }
            }

            // RECORDS SCREEN
            "records" -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "Labor Records",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (laborRecords.isEmpty()) {

                        Text(
                            text = "No records found",
                            fontSize = 18.sp
                        )

                    } else {

                        laborRecords.forEach { record ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),

                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),

                                shape = RoundedCornerShape(15.dp)
                            ) {

                                Text(
                                    text = record,
                                    modifier = Modifier.padding(16.dp),
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { currentScreen = "home" },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),

                        shape = RoundedCornerShape(15.dp)
                    ) {

                        Text("Back")
                    }
                }
            }
        }
    }
}