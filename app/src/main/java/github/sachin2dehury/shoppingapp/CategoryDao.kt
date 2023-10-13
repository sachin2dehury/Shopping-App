package github.sachin2dehury.shoppingapp

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CategoryDao {
    @Upsert
    suspend fun addItems(category: List<Category>)

    @Query("select * from category")
    suspend fun fetchItems(): List<Category>
}