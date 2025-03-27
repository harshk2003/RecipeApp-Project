package com.example.myrecepieapp

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(viewModel: MainViewModel, onNext: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    val cuisine by viewModel.cuisine
    val recipeState by viewModel.categoriesState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = isVisible) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.chef_hat_100dp_000000_fill0_wght400_grad0_opsz48), contentDescription = "null")
                Spacer(modifier = Modifier.height(16.dp))
                Text("What Cuisine Would You Like To Try?", fontSize = 20.sp)

                OutlinedTextField(
                    value = cuisine,
                    onValueChange = { viewModel.updateCuisine(it) },
                    label = { Text("Eg. Italian") }
                )

                IconButton(
                    onClick = {
                        isVisible = false
                        viewModel.fetchRecipes()
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "null")
                }
            }
        }

        if (!isVisible) {
            when {
                recipeState.loading -> CircularProgressIndicator()
                recipeState.error != null -> Text("Error: ${recipeState.error}", color = Color.Red)
                else -> onNext()  // Switch to RecipeScreen
            }
        }
    }
}

