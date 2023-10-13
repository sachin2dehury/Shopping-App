package github.sachin2dehury.shoppingapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDao {

    @Upsert
    suspend fun addItem(item: Item)

    @Query("select * from fav")
    fun fetchItems(): Flow<List<Item>>

    @Delete
    suspend fun deleteItem(item: Item): Int
}