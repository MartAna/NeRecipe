package ru.netology.nerecipe.data

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

interface RecipeRepository {
    val dataRecipe: LiveData<List<Recipe>>
    val dataStep: LiveData<List<Step>>
    fun like(id: Long)
    fun saveRecipe(recipe: Recipe)
    fun deleteRecipe(id: Long)
    fun deleteStep(id: Long)
    fun saveStep(steps: Step)
    fun lastId():Long

    companion object {
        const val newRecipeId = 0L
    }
}