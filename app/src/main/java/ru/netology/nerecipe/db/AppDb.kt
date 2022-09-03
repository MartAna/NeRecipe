package ru.netology.nerecipe.db

import android.content.Context

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class AppDb : RoomDatabase() {
    abstract val postDao: PostDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context, AppDb::class.java, "app.db"
            ).allowMainThreadQueries()
                .build()
    }
}