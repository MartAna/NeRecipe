package ru.netology.nerecipe.data

class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val category: String,
)

class Step(
    val stepId: Long,
    val recipeId: Long,
    val image: String?,
    val description: String
)