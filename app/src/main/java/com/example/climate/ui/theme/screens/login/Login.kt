package com.example.climate.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.climate.data.AuthViewModel
import com.example.climate.navigation.ROUTE_REGISTER

// LoginScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val pass = remember { mutableStateOf(TextFieldValue("")) }
    var context = LocalContext.current

    Surface(color = MaterialTheme.colorScheme.background) { // Use MaterialTheme colors
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Consistent padding
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login Here",
                style = MaterialTheme.typography.titleSmall, // Use MaterialTheme typography
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") }, // Shorter label
                placeholder = { Text("Enter Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pass.value,
                onValueChange = { pass.value = it },
                label = { Text("Password") }, // Shorter label
                placeholder = { Text("Enter Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(), // Hide password
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val mylogin= AuthViewModel(navController, context )
                    mylogin.login(email.value.text.trim(),pass.value.text.trim())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), // Add some horizontal padding
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary // Use MaterialTheme colors
                )
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(ROUTE_REGISTER) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                colors = ButtonDefaults.textButtonColors( // Text button style
                    contentColor = MaterialTheme.colorScheme.primary // Use MaterialTheme colors
                )
            ) {
                Text(text = "Don't have an account? Click to Register")
            }
        }
    }
}
