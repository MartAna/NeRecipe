package ru.netology.nerecipe.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.RecipeItemBinding
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipesAdapter(
    private val interactionListener: RecipeViewModel
) : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(
            inflater, parent, false
        )
        return RecipeViewHolder (binding, interactionListener)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    class RecipeViewHolder(
        private val binding: RecipeItemBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var recipe: Recipe

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.menuMore).apply {
                inflate(R.menu.options_recipe)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onDeleteClicked(recipe)
                            true
                        }
                        R.id.edit -> {
                            listener.onEditClicked(recipe)
                            true
                        }
                        else -> false
                    }
                }
            }
        }

        init {
            binding.recipeItemContainer.setOnClickListener {
                listener.onRecipeClicked(recipe)
            }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                textTitle.text = recipe.title
                textAuthor.text = recipe.author
                textCategory.text = recipe.category
                menuMore.setOnClickListener { popupMenu.show() }
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Recipe>() {

        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }


}