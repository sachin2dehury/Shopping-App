package github.sachin2dehury.shoppingapp.data


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val categories: List<Category?>? = null,
    val error: Any? = null,
    val message: String? = null,
    val status: Boolean? = null
)

