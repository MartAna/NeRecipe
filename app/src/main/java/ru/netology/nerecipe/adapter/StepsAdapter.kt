package ru.netology.nerecipe.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.databinding.StepItemBinding
import ru.netology.nerecipe.dto.Step

class StepsAdapter(
    private val interactionListener: RecipeInteractionListener
    ) : ListAdapter<Step, StepsAdapter.StepViewHolder>(DiffCallbackStep) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StepItemBinding.inflate(
            inflater, parent, false
        )
        return StepViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = getItem(position)
        holder.bindStep(step)
    }

    class StepViewHolder(
        private val binding: StepItemBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var step: Step
        fun bindStep(step: Step) {
            this.step = step
            with(binding) {
                stepText.text = step.description
            }
        }

        init {
            binding.stepItemContainer.setOnClickListener {
                listener.onStepClicked(step)
            }
        }
    }
}

private object DiffCallbackStep : DiffUtil.ItemCallback<Step>() {

    override fun areItemsTheSame(oldItem: Step, newItem: Step) =
        oldItem.stepId == newItem.stepId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Step, newItem: Step) =
        oldItem == newItem
}