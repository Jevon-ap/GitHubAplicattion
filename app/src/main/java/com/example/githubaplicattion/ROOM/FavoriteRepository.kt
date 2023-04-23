package com.example.githubaplicattion.ROOM

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository (application: Application){
    private val favoriteDao: FavoriteDao
    private var userDb: UserDatabase

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        userDb = UserDatabase.getDatabase(application)
        favoriteDao = userDb.favoriteDao()
    }

    fun getFavorite(): LiveData<List<FavoritUser>> {
        return favoriteDao.getFavorite()
    }


    fun insertFavorite(username: String, id: Int, avatarUrl: String) {
        executorService.execute {
            var user = FavoritUser(
                username,
                id,
                avatarUrl
            )
            favoriteDao.addFavorite(user)
        }
    }

    fun checkUser(id: Int) = favoriteDao?.checkUser(id)

    fun deleteFavorite(id: Int) {
        executorService.execute{ favoriteDao?.deleteFavorite(id) }

    }
}