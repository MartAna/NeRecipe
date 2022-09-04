package ru.netology.nerecipe.data

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.dto.Recipe

interface RecipeRepository {
    val data: LiveData<List<Recipe>>
    fun addRecipe()
    fun like()
    fun save()
    fun delete()

    companion object {
        const val newRecipeId = 0L
    }
}