package github.sachin2dehury.shoppingapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(ShoppingDbConverter::class)
@Database(
    entities = [CartItem::class, Item::class, Category::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract val favDao: FavDao
    abstract val cartDao: CartDao
    abstract val categoryDao: CategoryDao
}