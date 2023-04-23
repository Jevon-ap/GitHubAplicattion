package com.example.githubaplicattion.ROOM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

interface FavoriteDao {

    @Insert
    fun addFavorite(favoriteUser: FavoritUser)

    @Query("SELECT * FROM favoriteuser")
    fun getFavorite():LiveData<List<FavoritUser>>

    @Query("SELECT count(*) FROM favoriteuser WHERE favoriteuser.id = :id")
    fun checkUser(id: Int): Int

    @Query("DELETE FROM favoriteuser WHERE favoriteuser.id = :id")
    fun deleteFavorite(id: Int): Int
}