package com.example.appviagens.ui.theme

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.appviagens.R
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.viagem_de_negocios), // Substitua pelo seu logo
            contentDescription = "Logo do App",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nome de Usuário") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (!Patterns.EMAIL_ADDRESS.matcher(it).matches() && it.isNotEmpty()) {
                    "Digite um e-mail válido!"
                } else ""
            },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        if (emailError.isNotEmpty()) {
            Text(text = emailError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = when {
                    it.length < 6 -> "A senha deve ter pelo menos 6 caracteres!"
                    !it.any { char -> char.isDigit() } -> "A senha deve conter pelo menos um número!"
                    else -> ""
                }
            },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        if (passwordError.isNotEmpty()) {
            Text(text = passwordError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirme sua Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            when {
                username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(context, "As senhas não coincidem!", Toast.LENGTH_SHORT).show()
                }
                emailError.isNotEmpty() || passwordError.isNotEmpty() -> {
                    Toast.makeText(context, "Corrija os erros antes de registrar!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    navController.navigate("login")
                }
            }
        }) {
            Text(text = "Registrar", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}
