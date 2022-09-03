package ru.netology.nerecipe.db

import ru.netology.nerecipe.data.Recipe
import ru.netology.nerecipe.data.Step

internal fun RecipeEntity.toModel() = Recipe(
    id = id,
    author = author,
    title = title,
    category = category,
)

internal fun Recipe.toEntity() = RecipeEntity(
    id = id,
    author = author,
    title = title,
    category = category,
)

internal fun StepEntity.toModel() = Step(
    stepId = stepId,
    recipeId = recipeId,
    image = image,
    description = description
)

internal fun Step.toEntity() = StepEntity(
    stepId = stepId,
    recipeId = recipeId,
    image = image,
    description = description
)