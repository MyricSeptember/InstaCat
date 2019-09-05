package com.example.android.devbyteviewer.database

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
    abstract fun catDao(): CatDao
}
