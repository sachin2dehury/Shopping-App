package github.sachin2dehury.shoppingapp

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context, moshi: Moshi) =
        Room.databaseBuilder(context, ShoppingDatabase::class.java, "shop_db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(ShoppingDbConverter(moshi))
            .build()

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideCartDao(db: ShoppingDatabase) = db.cartDao

    @Provides
    @Singleton
    fun provideCategoryDao(db: ShoppingDatabase) = db.categoryDao

    @Provides
    @Singleton
    fun provideFavDao(db: ShoppingDatabase) = db.favDao
}