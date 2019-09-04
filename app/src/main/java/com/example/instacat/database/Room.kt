package com.example.android.devbyteviewer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatDao {
    @Query("select * from databasecat")
    fun getCats(): LiveData<List<DatabaseCat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg cats: DatabaseCat)
}

@Database(entities = [DatabaseCat::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract val catDao: CatDao
}

private lateinit var INSTANCE: CatDatabase

fun getDatabase(context: Context): CatDatabase {
    synchronized(CatDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CatDatabase::class.java,
                "cat"
            ).build()
        }
    }
    return INSTANCE
}