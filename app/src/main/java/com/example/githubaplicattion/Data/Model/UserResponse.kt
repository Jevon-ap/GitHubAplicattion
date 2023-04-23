package com.example.githubaplicattion.Data.Model

import com.google.gson.annotations.SerializedName


data class UserResponse(
    @field:SerializedName(value="items")
    val items : ArrayList<User>
)