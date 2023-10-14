package github.sachin2dehury.shoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val cartDao: CartDao,
    private val favDao: FavDao
) : ViewModel() {

    private val viewModelIOScope = viewModelScope + Dispatchers.IO

    val categoryData = categoryDao.fetchItems().stateIn(viewModelIOScope, SharingStarted.Eagerly, null)
    val favData = favDao.fetchItems().stateIn(viewModelIOScope, SharingStarted.Eagerly, null)

    fun toggleFav(item: Item) = viewModelIOScope.launch {
        if (favDao.deleteItem(item) == 0) {
            favDao.addItem(item)
        }
    }

    fun addToCart(item: Item) = viewModelIOScope.launch {
        val existingQuantity = cartDao.getItem(item.id)?.quantity ?: 0
        val cartItem = CartItem(
            icon = item.icon,
            id = item.id,
            name = item.name,
            price = item.price,
            quantity = existingQuantity + 1
        )
        cartDao.insert(cartItem)
    }

}