package ru.netology.nerecipe.data

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.RecipeWithStep
import ru.netology.nerecipe.dto.Step

interface RecipeRepository {
    val data: LiveData<List<RecipeWithStep>>
    fun addRecipe()
    fun like()
    fun save()
    fun delete()

    companion object {
        const val newRecipeId = 0L
    }
}