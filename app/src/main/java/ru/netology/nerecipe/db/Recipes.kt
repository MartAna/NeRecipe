package ru.netology.nerecipe.db

import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

internal fun RecipeEntity.toModel() = Recipe(
    id = id,
    author = author,
    title = title,
    category = category,
    likedByMe = likedByMe
)

internal fun Recipe.toEntity() = RecipeEntity(
    id = id,
    author = author,
    title = title,
    category = category,
    likedByMe = likedByMe
)

internal fun StepEntity.toModel() = Step(
    stepId = stepId,
    recipeId = recipeId,
    stepNumber = stepNumber,
    image = image,
    description = description
)

internal fun Step.toEntity() = StepEntity(
    stepId = stepId,
    recipeId = recipeId,
    stepNumber = stepNumber,
    image = image,
    description = description
)

