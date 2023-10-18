package github.sachin2dehury.shoppingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    val icon: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val price: Double? = null,
    val quantity: Int,
)