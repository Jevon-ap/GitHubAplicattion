package com.example.githubaplicattion.API

import com.example.githubaplicattion.Data.Model.DetailUserResponse
import com.example.githubaplicattion.Data.Model.User
import com.example.githubaplicattion.Data.Model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET(URL.user)

    fun User(): Call<UserResponse>

    @GET(URL.search)

    fun getSearchUsers(
        @Query(value = "q") query: String
    ): Call<UserResponse>

    @GET(URL.detail)

    fun getUserDetail(
        @Path("username") username:String
    ): Call<DetailUserResponse>

    @GET(URL.followers)

    fun getFollowers(
        @Path("username") username:String
    ):Call<ArrayList<User>>

    @GET(URL.following)

    fun getFollowing(
        @Path("username") username:String
    ):Call<ArrayList<User>>
}