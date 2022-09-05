package ru.netology.nerecipe.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.databinding.RecipeItemBinding
import ru.netology.nerecipe.databinding.StepEditItemBinding
import ru.netology.nerecipe.dto.Step

class EditStepAdapter(
    private val interactionListener: RecipeInteractionListener
) : ListAdapter<Step, EditStepAdapter.EditViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StepEditItemBinding.inflate(
            inflater, parent, false
        )
        return EditViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: EditViewHolder, position: Int) {
        val step = getItem(position)
        holder.bindEditStep(step)
    }

    class EditViewHolder(
        private val binding: StepEditItemBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var step: Step
        fun bindEditStep(step: Step?) {
            if (step != null) {
                this.step = step
                with(binding) {
                    stepText.setText(step.description)
                }
            }
        }

        init {
            binding.cancel.setOnClickListener {
                listener.onCancelClicked(step)
            }
        }

    }
}


private object DiffCallback : DiffUtil.ItemCallback<Step>() {

    override fun areItemsTheSame(oldItem: Step, newItem: Step) =
        oldItem.stepId == newItem.stepId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Step, newItem: Step) =
        oldItem == newItem
}