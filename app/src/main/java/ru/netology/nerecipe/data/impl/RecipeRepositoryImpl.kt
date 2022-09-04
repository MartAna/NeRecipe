package ru.netology.nerecipe.data.impl

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.dto.Recipe

class RecipeRepositoryImpl() : RecipeRepository {
    override val data: LiveData<List<Recipe>>
        get() = TODO("Not yet implemented")


    override fun addRecipe() {
        TODO("Not yet implemented")
    }

    override fun like() {
        TODO("Not yet implemented")
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}