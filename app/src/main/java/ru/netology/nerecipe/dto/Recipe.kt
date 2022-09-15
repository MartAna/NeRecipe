package ru.netology.nerecipe.dto

data class Recipe(
    val id: Long = 0,
    val title: String,
    val author: String,
    val category: Int,
    val likedByMe: Boolean = false,
)

data class Step(
    val stepId: Long = 0,
    val recipeId: Long = 0,
    val stepNumber: Long = 0,
    val image: String? = null,
    val description: String
)