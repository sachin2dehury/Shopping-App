package github.sachin2dehury.shoppingapp.cart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import github.sachin2dehury.shoppingapp.data.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Upsert
    suspend fun insert(cartItem: CartItem)

    @Query("select * from cart")
    fun fetchItems(): Flow<List<CartItem>>

    @Query("select * from cart where id=:id")
    suspend fun getItem(id: Int?): CartItem?

    @Delete
    suspend fun deleteItem(cartItem: CartItem)
}