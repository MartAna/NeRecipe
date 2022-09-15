package ru.netology.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.adapter.RecipeInteractionListener
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.data.impl.RecipeRepositoryImpl
import ru.netology.nerecipe.db.AppDb
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step
import ru.netology.nerecipe.util.SingleLiveEvent

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application),
    RecipeInteractionListener {


    private val repository: RecipeRepository = RecipeRepositoryImpl(
        dao = AppDb.getInstance(
            context = application
        ).recipeDao
    )

    val dataRecipe get() = repository.dataRecipe
    val dataStep get() = repository.dataStep
    val navigateToRecipe = SingleLiveEvent<Long>()
    val deleteRecipe = SingleLiveEvent<Unit>()
    val currentRecipe = MutableLiveData<Recipe?>(null)
    val currentStep = MutableLiveData<Step?>(null)

    override fun onDeleteClicked(recipe: Recipe) {
        repository.deleteRecipe(recipe.id)
        deleteRecipe.call()
    }

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
    }

    override fun onRecipeClicked(recipe: Recipe) {
        navigateToRecipe.value = recipe.id
    }

    override fun onLikeClicked(recipe: Recipe) {
        repository.like(recipe.id)
    }

    override fun onCancelClicked(step: Step) {
        currentStep.value = null
    }

    override fun onSaveClicked(newRecipe: Recipe, steps: List<Step>) {
        val recipe = currentRecipe.value?.copy(
            title = newRecipe.title,
            author = newRecipe.author,
            category = newRecipe.category
        ) ?: newRecipe
        repository.saveRecipe(recipe)
        currentRecipe.value = null
        val recipeId = repository.lastId()
        steps.map { step ->
            repository.saveStep(step.copy(recipeId = recipeId))
        }
    }

    override fun onStepClicked(step: Step) {
        currentStep.value = step
    }

    override fun onSaveEditStepClicked(step: Step) {
        repository.saveStep(step)
        currentStep.value = null
    }

    override fun onDeleteStepClicked(step: Step) {
        repository.deleteStep(step.stepId)
    }

}