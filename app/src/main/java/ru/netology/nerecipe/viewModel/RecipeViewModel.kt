package ru.netology.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.data.impl.RecipeRepositoryImpl
import ru.netology.nerecipe.db.AppDb

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: RecipeRepository = RecipeRepositoryImpl(
        dao = AppDb.getInstance(
            context = application
        ).recipeDao
    )

    val data get() = repository.data

}