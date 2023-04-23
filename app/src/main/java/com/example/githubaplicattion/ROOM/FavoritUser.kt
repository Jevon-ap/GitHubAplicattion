package com.example.githubaplicattion.ROOM

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "FavoriteUser")

@Parcelize
data class FavoritUser(
 @field:ColumnInfo(name = "login")
 val login:String = "",

 @field:ColumnInfo(name = "id")
 @PrimaryKey
 val id:Int,

 @field:ColumnInfo(name = "avatarUrl")
 var avatarUrl: String? = null,

): Parcelable
