package github.sachin2dehury.shoppingapp

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class ShoppingDbConverter(private val moshi: Moshi) {
    private val itemListType = Types.newParameterizedType(
        List::class.java,
        Item::class.java
    )

    @TypeConverter
    fun listToJSON(list: List<Item?>?): String? {
        return moshi.adapter<List<Item?>?>(itemListType).toJson(list)
    }

    @TypeConverter
    fun listFromJSON(json: String): List<Item?>? {
        return moshi.adapter<List<Item?>?>(itemListType).fromJson(json)
    }

}