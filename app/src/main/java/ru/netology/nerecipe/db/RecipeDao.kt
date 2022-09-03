package ru.netology.nerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.netology.nerecipe.dto.RecipeWithStep

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipes")
    fun getAll(): List<RecipeWithStep>?

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getRecipes(): LiveData<List<RecipeEntity>>

}