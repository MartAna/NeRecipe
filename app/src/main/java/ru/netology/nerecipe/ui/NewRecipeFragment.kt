package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapter.EditStepAdapter
import ru.netology.nerecipe.databinding.NewRecipeFragmentBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel
import java.lang.reflect.Array

class NewRecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NewRecipeFragmentBinding.inflate(layoutInflater, container, false)
        val adapter = EditStepAdapter(viewModel)
        binding.recyclerEditStep.adapter = adapter

        viewModel.currentRecipe.observe(viewLifecycleOwner) { currentRecipe ->
            val content = currentRecipe?.title
            if (content != null) {
                with(binding) {
                    editTitle.setText(currentRecipe.title)
                    editAuthor.setText(currentRecipe.author)
                    adapter.submitList(currentRecipe.steps.filter { it.recipeId == currentRecipe.id })
                }
            }
        }
        return binding.root
    }


}