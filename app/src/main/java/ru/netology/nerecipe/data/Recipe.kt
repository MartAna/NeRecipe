package ru.netology.nerecipe.data

class Recipe(
    val title: String,
    val author: String,
    val category: String,
    val steps: List<Step>
)

class Step(
    val image: Any?,
    val description: String
)