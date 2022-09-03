package ru.netology.nerecipe.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    @NonNull
    val title: String,
    @ColumnInfo(name = "author")
    @NonNull
    val author: String,
    @ColumnInfo(name = "category")
    @NonNull
    val category: String,
    @ColumnInfo(name = "liked_by_me")
    val likedByMe: Boolean = false
)

@Entity(
    tableName = "steps", foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("recipeId"),
        onDelete = CASCADE
    )]
)
class StepEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stepId")
    val stepId: Long,
    @ColumnInfo(name = "recipeId")
    @NonNull
    val recipeId: Long,
    @ColumnInfo(name = "stepNumber")
    @NonNull
    val stepNumber: Long,
    @ColumnInfo(name = "image")
    val image: String? = null,
    @ColumnInfo(name = "description")
    @NonNull
    val description: String
)

