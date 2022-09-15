package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapter.StepsAdapter
import ru.netology.nerecipe.databinding.NewRecipeFragmentBinding
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.dto.Step
import ru.netology.nerecipe.viewModel.RecipeViewModel

class NewRecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    private var steps = emptyList<Step>()
    private lateinit var step: Step

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NewRecipeFragmentBinding.inflate(layoutInflater, container, false)
        val adapter = StepsAdapter(viewModel)
        binding.recyclerEditStep.adapter = adapter

        editRecipe(binding, adapter)

        val spinner = binding.spinner
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.Category,
                android.R.layout.simple_spinner_item
            ).also { adapterSpinner ->
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapterSpinner
            }
        }
        spinner.setSelection(0)

        binding.stepLayout.stepEditText.editableText
        binding.fabAddStep.setOnClickListener {
            newStep(adapter, binding)
            binding.stepLayout.stepEditText.text.clear()

        }

        binding.save.setOnClickListener {
            save(binding)
            findNavController().navigateUp()
        }

        return binding.root


    }

    private fun newStep(adapter: StepsAdapter, binding: NewRecipeFragmentBinding) {
        val oneStep: Step
        val number = steps.size
        oneStep = Step(
            stepNumber = number + 1L,
            description = binding.stepLayout.stepEditText.text.toString()
        )
        steps = steps + oneStep
        adapter.submitList(steps)
    }


    private fun save(binding: NewRecipeFragmentBinding) {
        with(binding) {
            val newRecipe = Recipe(
                title = editTitle.text.toString(),
                author = editAuthor.text.toString(),
                category = spinner.selectedItemPosition
            )
            viewModel.onSaveClicked(newRecipe, steps)
        }
    }

    private fun editRecipe(binding: NewRecipeFragmentBinding, adapter: StepsAdapter) {
        viewModel.currentRecipe.observe(viewLifecycleOwner) { currentRecipe ->
            val content = currentRecipe?.title
            if (content != null) {
                with(binding) {
                    editTitle.setText(currentRecipe.title)
                    editAuthor.setText(currentRecipe.author)
                    spinner.setSelection(currentRecipe.category)
                    viewModel.dataStep.observe(viewLifecycleOwner) { steps ->
                        adapter.submitList(steps.filter { it.recipeId == currentRecipe.id })
                    }
                }
            }
        }

        viewModel.currentStep.observe(viewLifecycleOwner){ currentStep ->
            val step = currentStep?.description
            if (step != null) {
                with(binding){
                    stepLayout.stepEditText.setText(currentStep.description)
                    stepLayout.groupButton.visibility = View.VISIBLE
                }

            }

        }
    }
}
