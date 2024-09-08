package com.spcreations.littlelemon.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName="menu_items")
data class MenuItems(
    @PrimaryKey val id: Int,
    val title: String,
    val description : String,
    val price: String,
    val image: String,
    val category: String

)

@Dao
interface MenuItemsDao{

    @Query("SELECT * FROM menu_items")
    fun getMenu() : LiveData<List<MenuItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menuItem: MenuItems)

}

@Database(entities=arrayOf(MenuItems::class),version=1, exportSchema = false)
abstract class MenuDataBase: RoomDatabase(){

    abstract fun menuItemDao(): MenuItemsDao

    companion object{

        private var INSTANCE: MenuDataBase? = null

        fun getDatabase(context: Context): MenuDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuDataBase::class.java,
                    "menu_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}

