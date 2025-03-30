package com.example.myrecepieapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val cuisine = mutableStateOf("")
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState = _categoriesState

    fun updateCuisine(newCuisine: String) {
        cuisine.value = newCuisine
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            try {
                _categoriesState.value = RecipeState(loading = true)

                val response = api.getRecipe(
                    GeminiRequest(
                        contents = listOf(
                            MessageContent(
                                parts = listOf(
                                    MessagePart(
                                        text = "Provide a JSON list of **only 4** dishes in ${cuisine.value} cuisinee. Format:\n" +
                                                "{\n" +
                                                "  \"categories\": [\n" +
                                                "    {\n" +
                                                "      \"idCategory\": \"1\",\n" +
                                                "      \"strCategory\": \"Dish Name\",\n" +
                                                "      \"strCategoryThumb\": \"Dish Image\",\n" +
                                                "      \"strCategoryDescription\": \"Dish description\",\n" +
                                                "      \"strRecipe\": \"Dish Recipe.\"\n" +
                                                "    }\n" +
                                                "  ]\n" +
                                                "}"
                                    )
                                )
                            )
                        )
                    )
                )

                val categories = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                Log.d("MainViewModel", "Raw API Response: $categories")

                _categoriesState.value = RecipeState(loading = false, list = parseCategories(categories))
            } catch (e: Exception) {
                _categoriesState.value = RecipeState(loading = false, error = e.localizedMessage)
            }
        }
    }

    private fun parseCategories(jsonString: String?): List<Category> {
        return try {
            if (jsonString.isNullOrBlank()) return emptyList()

            val cleanJson = jsonString.replace("```json", "").replace("```", "").trim()

            val jsonStart = cleanJson.indexOf("{")
            if (jsonStart != -1) {
                val pureJson = cleanJson.substring(jsonStart)
                return Gson().fromJson(pureJson, CategoriesResponse::class.java).categories
            }
            emptyList()
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error parsing JSON: ${e.message}")
            emptyList()
        }
    }
}


data class RecipeState(
    val loading: Boolean = false,
    val list: List<Category> = emptyList(),
    val error: String? = null
)
