package github.sachin2dehury.shoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val cartDao: CartDao,
    private val favDao: FavDao
) : ViewModel() {

    private val viewModelIOScope = viewModelScope + Dispatchers.IO

    val favData = favDao.fetchItems()

    fun removeItem(item: Item) = viewModelIOScope.launch {
        favDao.deleteItem(item)
    }

    fun moveToCart(item: Item) = viewModelIOScope.launch {
        val existingQuantity = cartDao.getItem(item.id)?.quantity ?: 0
        val cartItem = CartItem(
            icon = item.icon,
            id = item.id,
            name = item.name,
            price = item.price,
            quantity = existingQuantity + 1
        )
        cartDao.insert(cartItem)
        favDao.deleteItem(item)
    }
}