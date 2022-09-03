package ru.netology.nerecipe.dto

import androidx.room.Embedded
import androidx.room.Relation

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

class RecipeWithStep(
    @Embedded
    val recipe: Recipe,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val steps: List<Step>
)