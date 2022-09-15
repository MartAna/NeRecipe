package ru.netology.nerecipe.data.impl

import androidx.lifecycle.map
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.db.*
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

class RecipeRepositoryImpl(
    private val dao: RecipeDao
) : RecipeRepository {

    override val dataRecipe = dao.getAllRecipes().map { entities ->
        entities.map { it.toModel() }
    }

    override val dataStep = dao.getAllSteps().map { entities ->
        entities.map { it.toModel() }
    }

    override fun saveRecipe(recipe: Recipe) {
        if (recipe.id == RecipeRepository.newRecipeId) dao.insert(recipe.toEntity())
        else dao.updateRecipeById(recipe.id, recipe.title, recipe.author, recipe.category)
    }

    override fun delete(id: Long) {
        dao.removeById(id)
    }

    override fun saveStep(step: Step) {
        if (step.stepId == RecipeRepository.newRecipeId) dao.insert(step.toEntity())
        else dao.updateStepById(step.stepId, step.image, step.description)
    }

    override fun lastId() =
        dao.lastId()


    override fun like(id: Long) {
        dao.likeById(id)
    }


}
