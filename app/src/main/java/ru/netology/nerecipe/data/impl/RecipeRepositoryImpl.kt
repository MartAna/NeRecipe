package ru.netology.nerecipe.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step

class RecipeRepositoryImpl() : RecipeRepository {

    private var steps = listOf(
        Step(
            1,
            1,
            1,
            null,
            "Разбить два яйца"
        ),
        Step(
            2,
            2,
            1,
            null,
            "Почистить картошку"
        )
    )

    private var recipes = listOf(
        Recipe(
        1,
        "Яичница",
        "Я",
        "Русская",
        false,
        steps
        ),
        Recipe(
            2,
            "Картошка",
            "Я",
            "Русская",
            true,
            steps
        )
    )

    override val dataRecipe = MutableLiveData(recipes)
    override val dataStep = MutableLiveData(steps)

    override fun addRecipe() {
        TODO("Not yet implemented")
    }

    override fun like(id: Long) {
        recipes = recipes.map{
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
        }
        dataRecipe.value = recipes
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        recipes = recipes.filter { it.id != id }
        dataRecipe.value = recipes
    }
}