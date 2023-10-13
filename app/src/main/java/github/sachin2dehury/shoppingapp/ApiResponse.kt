package github.sachin2dehury.shoppingapp


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val categories: List<Category?>? = null,
    val error: Any? = null,
    val message: String? = null,
    val status: Boolean? = null
)

@Entity(tableName = "category")
@JsonClass(generateAdapter = true)
data class Category(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val items: List<Item?>? = null,
    val name: String? = null
)

@Entity(tableName = "fav")
@JsonClass(generateAdapter = true)
data class Item(
    val icon: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val price: Double? = null
)

@Entity(tableName = "cart")
data class CartItem(
    val icon: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val price: Double? = null,
    val quantity: Int,
)

