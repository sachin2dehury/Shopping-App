package github.sachin2dehury.shoppingapp.category

import github.sachin2dehury.shoppingapp.data.Item

interface ItemClickListener {
    fun toggleLike(item: Item)
    fun addToCart(item: Item)
}