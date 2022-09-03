package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nerecipe.adapter.RecipesAdapter
import ru.netology.nerecipe.databinding.RecipesFragmentBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipesFragment: Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RecipesFragmentBinding.inflate(layoutInflater,container,false)
        val adapter = RecipesAdapter(viewModel)

        return binding.root
    }
}