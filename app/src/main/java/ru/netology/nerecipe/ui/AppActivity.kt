package ru.netology.nerecipe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.AppActivityBinding

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = AppActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.recipeMenu -> {
                   findNavController(R.id.nav_host_fragment).navigate(
                       R.id.recipesFragment
                   )
                        true
                }
                R.id.loveRecipeMenu -> {
                    findNavController(R.id.nav_host_fragment).navigate(
                        R.id.loveRecipeFragment
                    )
                    true
                } else -> false
            }
        }
    }
}