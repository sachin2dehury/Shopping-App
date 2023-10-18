package github.sachin2dehury.shoppingapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.sachin2dehury.shoppingapp.cart.CartDao
import github.sachin2dehury.shoppingapp.category.CategoryDao
import github.sachin2dehury.shoppingapp.data.CartItem
import github.sachin2dehury.shoppingapp.data.Category
import github.sachin2dehury.shoppingapp.data.Item
import github.sachin2dehury.shoppingapp.fav.FavDao

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