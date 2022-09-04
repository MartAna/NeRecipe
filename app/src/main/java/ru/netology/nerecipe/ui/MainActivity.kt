package ru.netology.nerecipe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomMenu.setOnNavigationItemSelectedListener {
           when (it.itemId) {
                R.id.recipeMenu -> {
                    supportFragmentManager.commit {
                        replace(R.id.containerFragment, RecipesFragment())
                    }
                    true
                }

                R.id.loveRecipeMenu -> {
                    supportFragmentManager.commit {
                        replace(R.id.containerFragment, LoveRecipeFragment())
                    }
                    true
                }
                else -> false
            }
        }

       /* BottomNavigationView.OnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.recipeMenu -> {
                    supportFragmentManager.commit {
                        replace(R.id.containerFragment, RecipesFragment())
                        true
                    }
                }
                R.id.loveRecipeMenu -> {
                    supportFragmentManager.commit {
                        replace(R.id.containerFragment, LoveRecipeFragment())
                        true
                    }
                }
            }
        }*/
    }
}