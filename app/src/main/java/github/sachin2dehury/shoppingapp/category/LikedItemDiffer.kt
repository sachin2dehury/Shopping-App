package github.sachin2dehury.shoppingapp.category

import androidx.recyclerview.widget.DiffUtil
import github.sachin2dehury.shoppingapp.data.LikedItem

class LikedItemDiffer : DiffUtil.ItemCallback<LikedItem>() {
    override fun areItemsTheSame(oldItem: LikedItem, newItem: LikedItem): Boolean {
        return oldItem.item.id == newItem.item.id
    }

    override fun areContentsTheSame(oldItem: LikedItem, newItem: LikedItem): Boolean {
        return oldItem == newItem
    }
}