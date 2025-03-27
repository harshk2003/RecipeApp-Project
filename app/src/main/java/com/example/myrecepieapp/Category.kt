package com.example.myrecepieapp

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
    val strIngredients: String
)

data class CategoriesResponse(val categories: List<Category>)
