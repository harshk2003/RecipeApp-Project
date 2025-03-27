package com.example.myrecepieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myrecepieapp.ui.theme.MyRecepieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRecepieAppTheme {
                Scaffold { innerPadding -> AppContent(modifier = Modifier.padding(innerPadding))}
            }
        }
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    var currentScreen by remember { mutableStateOf("main") }
    var selectedRecipe by remember { mutableStateOf<Category?>(null) }

    when (currentScreen) {
        "main" -> MainScreen(viewModel) {
            currentScreen = "recipes"
        }

        "recipes" -> RecipeScreen(
            viewModel = viewModel,
            onRecipeClick = { recipe ->
                selectedRecipe = recipe
                currentScreen = "details"
            },
            onBackPress = {
                currentScreen = "main" // Navigate back to main screen when back is pressed
            }
        )

        "details" -> RecipeDetailsScreen(
            recipe = selectedRecipe!!,
            onBackPress = {
                currentScreen = "recipes" // Navigate back to recipe list when back is pressed
            }
        )
    }
}
