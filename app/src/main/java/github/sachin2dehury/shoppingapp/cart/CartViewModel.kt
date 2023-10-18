package github.sachin2dehury.shoppingapp.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.shoppingapp.data.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartDao: CartDao
) : ViewModel() {

    private val viewModelIOScope = viewModelScope + Dispatchers.IO

    val cartData = cartDao.fetchItems().stateIn(viewModelIOScope, SharingStarted.Eagerly, null)

    fun incrementItem(cartItem: CartItem) = viewModelIOScope.launch {
        cartDao.insert(cartItem.copy(quantity = cartItem.quantity + 1))
    }

    fun decrementItem(cartItem: CartItem) = viewModelIOScope.launch {
        val quantity = cartItem.quantity
        if (quantity > 1) {
            cartDao.insert(cartItem.copy(quantity = quantity - 1))
        } else {
            cartDao.deleteItem(cartItem)
        }
    }
}

