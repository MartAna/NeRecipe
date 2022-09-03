package ru.netology.nerecipe.data.impl

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.db.RecipeDao
import ru.netology.nerecipe.dto.RecipeWithStep

class RecipeRepositoryImpl(
    private val dao: RecipeDao
) : RecipeRepository {
    override val data: LiveData<List<RecipeWithStep>>
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