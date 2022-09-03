package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.netology.nerecipe.databinding.RecipeFragmentBinding
import ru.netology.nerecipe.databinding.RecipesFragmentBinding

class RecipeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RecipeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}