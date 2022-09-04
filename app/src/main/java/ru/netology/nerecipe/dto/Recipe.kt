package ru.netology.nerecipe.dto

class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val category: String,
    val likedByMe: Boolean
)

class Step(
    val stepId: Long,
    val recipeId: Long,
    val stepNumber: Long,
    val image: String?,
    val description: String
)