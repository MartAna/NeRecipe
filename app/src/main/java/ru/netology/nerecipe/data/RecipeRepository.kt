package ru.netology.nerecipe.data

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

interface RecipeRepository {
    val dataRecipe: LiveData<List<Recipe>>
    val dataStep: LiveData<List<Step>>
    fun addRecipe()
    fun like(id: Long)
    fun save()
    fun delete(id: Long)

    companion object {
        const val newRecipeId = 0L
    }
}