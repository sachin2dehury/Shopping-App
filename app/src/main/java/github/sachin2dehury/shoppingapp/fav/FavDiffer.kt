package github.sachin2dehury.shoppingapp.fav

import androidx.recyclerview.widget.DiffUtil
import github.sachin2dehury.shoppingapp.data.Item

class FavDiffer : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}