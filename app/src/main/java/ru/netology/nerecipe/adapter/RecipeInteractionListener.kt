package ru.netology.nerecipe.adapter

import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

interface RecipeInteractionListener {
    fun onDeleteClicked(recipe: Recipe)
    fun onEditClicked(recipe: Recipe)
    fun onRecipeClicked(recipe: Recipe)
    fun onLikeClicked(recipe: Recipe)
    fun onCancelClicked(step: Step)
    fun onSaveClicked(recipe: Recipe, steps: List<Step>)
    fun onStepClicked(step: Step)
}