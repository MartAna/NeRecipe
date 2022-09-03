package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.netology.nerecipe.databinding.LoveRecipeFragmentBinding


class LoveRecipeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LoveRecipeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}