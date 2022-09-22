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
import ru.netology.nerecipe.adapter.StepsAdapter
import ru.netology.nerecipe.databinding.RecipeFragmentBinding
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.util.LongArg
import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    lateinit var recipe: Recipe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RecipeFragmentBinding.inflate(layoutInflater, container, false)
        val viewHolder = RecipesAdapter.RecipeViewHolder(binding = binding.recipeLayout, viewModel)
        val id = arguments?.longArg

        viewModel.dataRecipe.value?.map {
            if (it.id == id) {
                recipe = it
            }
        }

        viewModel.dataRecipe.observe(viewLifecycleOwner) {
            it.map { recipe ->
                if (recipe.id == id) {
                    viewHolder.bind(recipe)
                }
            }
        }

        viewModel.deleteRecipe.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        val adapterStep = StepsAdapter(viewModel)
        binding.stepsRecyclerView.adapter = adapterStep
        viewModel.dataStep.observe(viewLifecycleOwner) { steps ->
            adapterStep.submitList(steps.filter { it.recipeId == id })
        }

        viewModel.currentRecipe.observe(viewLifecycleOwner) { currentRecipe ->
            val content = currentRecipe?.title
            if (content != null) {
                if (content.isNotEmpty()) {
                    findNavController().navigate(
                        R.id.action_recipeFragment_to_newRecipeFragment,
                        Bundle().apply {
                            longArg = currentRecipe.id
                        }
                    )
                }
            }
        }
        return binding.root
    }

    companion object {
        var Bundle.longArg: Long? by LongArg
    }
}