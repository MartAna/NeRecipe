package ru.netology.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.adapter.RecipeInteractionListener
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.data.impl.RecipeRepositoryImpl
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step
import ru.netology.nerecipe.util.SingleLiveEvent

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application),
    RecipeInteractionListener {


    private val repository: RecipeRepository = RecipeRepositoryImpl()

    val dataRecipe get() = repository.dataRecipe
    val dataStep get() = repository.dataStep
    fun like(id: Long) = repository.like(id)
    val navigateToRecipe = SingleLiveEvent<Long>()
    val deleteRecipe = SingleLiveEvent<Unit>()
    val currentRecipe = MutableLiveData<Recipe?>(null)

    override fun onDeleteClicked(recipe: Recipe) {
        repository.delete(recipe.id)
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
        TODO("Not yet implemented")
    }


}