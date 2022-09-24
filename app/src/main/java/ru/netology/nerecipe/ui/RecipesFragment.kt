package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapter.RecipesAdapter
import ru.netology.nerecipe.databinding.RecipesFragmentBinding
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.util.ArrayArg
import ru.netology.nerecipe.util.LongArg
import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipesFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    lateinit var recipes: List<Recipe>
    lateinit var result: List<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RecipesFragmentBinding.inflate(layoutInflater, container, false)
        val adapter = RecipesAdapter(viewModel)
        binding.recipesRecyclerView.adapter = adapter

        val selectedCategory = arguments?.arrayArg

        viewModel.dataRecipe.observe(viewLifecycleOwner) {
            recipes = it
            if (recipes.isEmpty()) {
                binding.groupNull.visibility = View.VISIBLE
            } else binding.groupNull.visibility = View.GONE
            adapter.submitList(recipes)
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

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                result = listOf()
                recipes.map {
                    if (it.title.startsWith(query)) {
                        result = result + it
                        adapter.submitList(result)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                result = listOf()
                recipes.map {
                    if (newText != null) {
                        if (it.title.startsWith(newText)) {
                            result = result + it
                            adapter.submitList(result)
                        }
                    }
                }
                return false
            }
        }
        )

        binding.filter.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_filterDialogFragment)
            var selectedRecipe = listOf<Recipe>()
            selectedCategory?.map {
                recipes.map { recipe ->
                    if (recipe.category == it) {
                        selectedRecipe = selectedRecipe + recipe
                    }
                }
            }
            adapter.submitList(selectedRecipe)
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

    companion object {
        var Bundle.longArg: Long? by LongArg
        var Bundle.arrayArg: ArrayList<Int>? by ArrayArg
    }
}
