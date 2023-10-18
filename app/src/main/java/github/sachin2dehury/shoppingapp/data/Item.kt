package github.sachin2dehury.shoppingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "fav")
@JsonClass(generateAdapter = true)
data class Item(
    val icon: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val price: Double? = null
)