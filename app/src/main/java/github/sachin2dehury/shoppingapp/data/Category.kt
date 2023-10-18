package github.sachin2dehury.shoppingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "category")
@JsonClass(generateAdapter = true)
data class Category(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val items: List<Item?>? = null,
    val name: String? = null
)