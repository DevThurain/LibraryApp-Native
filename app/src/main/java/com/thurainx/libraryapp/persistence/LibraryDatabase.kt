package com.thurainx.libraryapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.persistence.daos.BookListDao
import com.thurainx.libraryapp.persistence.daos.RecentBookDao

@Database(entities = [BookVO::class, BookListVO::class], version = 1, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "THE_LIBRARY_DATABASE"
        var dbInstant : LibraryDatabase? = null

        fun getDBInstant(context: Context) : LibraryDatabase?{
            when(dbInstant){
                null -> {
                    dbInstant = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstant
        }
    }

    abstract fun recentBookDao() : RecentBookDao
    abstract fun bookListDao() : BookListDao

}