package com.thurainx.libraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO

@Dao
interface ShelfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelf(shelfVO: ShelfVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelfList(shelfList: List<ShelfVO>)

    @Query("SELECT * FROM shelves")
    fun getAllShelves(): LiveData<List<ShelfVO>>

    @Query("SELECT * FROM shelves WHERE name = :name")
    fun getShelfByName(name: String): LiveData<ShelfVO>

    @Query("DELETE FROM shelves")
    fun deleteAllShelves()

}


