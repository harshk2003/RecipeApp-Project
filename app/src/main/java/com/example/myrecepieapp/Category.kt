package com.example.myrecepieapp

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
    val strRecipe: String
)

data class CategoriesResponse(val categories: List<Category>)
