package ru.netology.nerecipe.adapter

import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

interface RecipeInteractionListener {
    fun onDeleteClicked(recipe: Recipe)
    fun onEditClicked(recipe: Recipe)
    fun onRecipeClicked(recipe: Recipe)
    fun onLikeClicked(recipe: Recipe)
    fun onCancelClicked()
    fun onSaveClicked(newRecipe: Recipe, steps: List<Step>)
    fun onStepClicked(step: Step)
    fun onSaveEditStepClicked(step: Step)
    fun onDeleteStepClicked(stepId: Long) {
    }
}