package ru.netology.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.nerecipe.adapter.RecipeInteractionListener
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.data.impl.RecipeRepositoryImpl
import ru.netology.nerecipe.dto.Recipe

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application),
    RecipeInteractionListener {

    private val repository: RecipeRepository = RecipeRepositoryImpl()

    val data get() = repository.data
    override fun onDeleteClicked(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override fun onEditClicked(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override fun onRecipeClicked(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override fun onLikeClicked(recipe: Recipe) {
        TODO("Not yet implemented")
    }

}