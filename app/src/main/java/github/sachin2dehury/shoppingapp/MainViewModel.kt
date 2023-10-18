package github.sachin2dehury.shoppingapp

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.shoppingapp.category.CategoryDao
import github.sachin2dehury.shoppingapp.data.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moshi: Moshi,
    private val categoryDao: CategoryDao
) : ViewModel() {

    private val viewModelIOScope = viewModelScope + Dispatchers.IO

    fun addDataToDb(assets: AssetManager) = viewModelIOScope.launch {
        val json = assets.open("shopping.json").bufferedReader().use { it.readText() }
        val result = moshi.adapter(ApiResponse::class.java).fromJson(json)
        result?.categories?.filterNotNull()?.let {
            categoryDao.addItems(it)
        }
    }
}