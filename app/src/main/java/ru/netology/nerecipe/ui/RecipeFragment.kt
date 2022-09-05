package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nerecipe.adapter.StepsAdapter
import ru.netology.nerecipe.databinding.RecipeFragmentBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipeFragment: Fragment() {

    private val viewModel: RecipeViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RecipeFragmentBinding.inflate(layoutInflater,container,false)

        val adapterStep = StepsAdapter()
        binding.stepsRecyclerView.adapter = adapterStep
        viewModel.dataStep.observe(viewLifecycleOwner) {
            adapterStep.submitList(it)
        }
        return binding.root
    }

}