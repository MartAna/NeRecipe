package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapter.RecipesAdapter
import ru.netology.nerecipe.databinding.RecipesFragmentBinding
import ru.netology.nerecipe.ui.RecipeFragment.Companion.longArg
import ru.netology.nerecipe.viewModel.RecipeViewModel
import kotlin.collections.emptyList as emptyList1

class RecipesFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RecipesFragmentBinding.inflate(layoutInflater, container, false)
        val adapter = RecipesAdapter(viewModel)
        binding.recipesRecyclerView.adapter = adapter

        viewModel.dataRecipe.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        createNewRecipe(binding)
        openPost()

        viewModel.currentRecipe.observe(viewLifecycleOwner) { currentRecipe ->
            val content = currentRecipe?.title
            if (content != null) {
                if (content.isNotEmpty()) {
                    findNavController().navigate(
                        R.id.action_recipesFragment_to_newRecipeFragment,
                        Bundle().apply {
                            longArg = currentRecipe.id
                        }
                    )
                }
            }
        }

        return binding.root
    }

    private fun createNewRecipe(binding: RecipesFragmentBinding) {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_newRecipeFragment)
        }
    }

    private fun openPost() {
        viewModel.navigateToRecipe.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(
                    R.id.action_recipesFragment_to_recipeFragment,
                    Bundle().apply {
                        longArg = it
                    }
                )
            }
        }
    }



}