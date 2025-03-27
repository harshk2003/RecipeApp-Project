package com.example.myrecepieapp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RecipeScreen(viewModel: MainViewModel, onRecipeClick: (Category) -> Unit, onBackPress: () -> Unit) {
    val state = viewModel.categoriesState.value

    // Handle back press correctly
    BackHandler {
        onBackPress() // Navigates back to main screen
    }

    when {
        state.loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        state.error != null -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Error: ${state.error}", color = Color.Red)
        }

        state.list.isEmpty() -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No recipes found", color = Color.Gray)
        }

        else -> {
            Spacer(modifier = Modifier.padding(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(state.list) { categoryItem ->
                    CategoryItem(category = categoryItem, onClick = { onRecipeClick(categoryItem) })
                }
            }
        }
    }
}


@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.strCategoryThumb,
            contentDescription = category.strCategory,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}


