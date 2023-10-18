package github.sachin2dehury.shoppingapp.category

import androidx.recyclerview.widget.DiffUtil
import github.sachin2dehury.shoppingapp.data.Category

class CategoryDiffer : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}