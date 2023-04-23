package com.example.githubaplicattion.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoritUser::class], version = 1)
abstract class UserDatabase :RoomDatabase(){

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null


        @JvmStatic
        fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "favorite_database"
                    )
                        .build()
                }
            }
            return INSTANCE as UserDatabase
        }
    }
}