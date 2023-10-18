package github.sachin2dehury.shoppingapp.cart

import github.sachin2dehury.shoppingapp.data.CartItem

interface CartClickListener {
    fun plus(item: CartItem)
    fun minus(item: CartItem)
}