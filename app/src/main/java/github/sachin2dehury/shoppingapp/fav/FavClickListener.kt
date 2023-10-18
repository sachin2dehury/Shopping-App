package github.sachin2dehury.shoppingapp.fav

import github.sachin2dehury.shoppingapp.data.Item

interface FavClickListener {
    fun unLike(item: Item)
    fun moveToCart(item: Item)
}