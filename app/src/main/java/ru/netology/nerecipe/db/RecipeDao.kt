package ru.netology.nerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM steps")
    fun getAllSteps(): LiveData<List<StepEntity>>

    @Insert
    fun insert(recipe: RecipeEntity): Long

    @Insert
    fun insert(step: StepEntity)

    @Query("UPDATE recipes SET title = :title, author = :author, category = :category  WHERE id = :id")
    fun updateRecipeById(id: Long, title: String, author: String, category: Int)

    @Query("UPDATE steps SET image = :image, description = :description WHERE stepId = :id")
    fun updateStepById(id: Long, image: String?, description: String)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun removeById(id: Long)

    @Query(
        """
        UPDATE recipes SET
        liked_by_me = CASE WHEN liked_by_me THEN 0 ELSE 1 END
        WHERE id = :id
    """
    )
    fun likeById(id: Long)

    @Query("SELECT MAX(id) FROM recipes")
    fun lastId(): Long
}