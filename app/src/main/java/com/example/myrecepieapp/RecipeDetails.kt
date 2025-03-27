package com.example.myrecepieapp


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun RecipeDetailsScreen(recipe: Category, onBackPress: () -> Unit) {
    BackHandler {
        onBackPress() // Navigates back to recipes screen
    }
    Spacer(modifier = Modifier.padding(100.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = recipe.strCategoryThumb,
            contentDescription = recipe.strCategory,
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = recipe.strCategory, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Details about ${recipe.strCategory}:", fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(15.dp))
        Text(text = recipe.strCategoryDescription, fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(15.dp))
        Text(text = "Ingredients:", fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(15.dp))
        Text(text = recipe.strIngredients, fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(15.dp))
        Button(onClick = onBackPress) {
            Text("Back")
        }
    }
}


