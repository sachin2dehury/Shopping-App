package github.sachin2dehury.shoppingapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CartDao {

    @Upsert
    suspend fun addItem(cartItem: CartItem)

    @Query("select * from cart")
    suspend fun fetchItems(): List<CartItem>

    @Delete
    suspend fun deleteItem(cartItem: CartItem)
}