package com.example.appviagens.ui.theme

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
import androidx.navigation.NavController
import com.example.appviagens.R

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.viagem_de_negocios),
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            when {
                username.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
                username.length < 3 -> {
                    Toast.makeText(context, "O nome de usuário deve ter pelo menos 3 caracteres!", Toast.LENGTH_SHORT).show()
                }
                password.length < 6 -> {
                    Toast.makeText(context, "A senha deve ter pelo menos 6 caracteres!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    navController.navigate("menu/$username")
                }
            }
        }) {
            Text(text = "Login", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = { navController.navigate("register") }) {
            Text(text = "Registrar um novo usuário", color = Color.Blue)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
