package ru.netology.nerecipe.dto

data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val category: String,
    val likedByMe: Boolean,
    val steps: List<Step>
)

data class Step(
    val stepId: Long,
    val recipeId: Long,
    val stepNumber: Long,
    val image: String?,
    val description: String
)