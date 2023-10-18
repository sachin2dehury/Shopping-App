package github.sachin2dehury.shoppingapp.data

import github.sachin2dehury.shoppingapp.data.Item

data class LikedItem(
    val item: Item,
    val liked: Boolean = false,
)