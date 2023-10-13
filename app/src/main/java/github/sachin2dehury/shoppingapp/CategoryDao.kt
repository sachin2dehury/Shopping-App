package github.sachin2dehury.shoppingapp

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Upsert
    suspend fun addItems(category: List<Category>)

    @Query("select * from category")
    fun fetchItems(): Flow<List<Category>>
}