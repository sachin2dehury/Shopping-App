package github.sachin2dehury.shoppingapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavDao {

    @Upsert
    suspend fun addItem(item: Item)

    @Query("select * from fav")
    suspend fun fetchItems(): List<Item>

    @Delete
    suspend fun deleteItem(item: Item)
}