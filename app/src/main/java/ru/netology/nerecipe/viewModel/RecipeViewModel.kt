package ru.netology.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.data.impl.RecipeRepositoryImpl

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: RecipeRepository = RecipeRepositoryImpl()

    val data get() = repository.data

}