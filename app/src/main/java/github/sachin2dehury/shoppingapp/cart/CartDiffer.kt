package github.sachin2dehury.shoppingapp.cart

import androidx.recyclerview.widget.DiffUtil
import github.sachin2dehury.shoppingapp.data.CartItem

class CartDiffer : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }
}