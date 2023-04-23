package com.example.githubaplicattion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.gifdecoder.R
import com.example.githubaplicattion.API.RetrofitClient
import com.example.githubaplicattion.Data.Model.User
import com.example.githubaplicattion.Data.Model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Query

class MainViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<User>>()

 fun getUser(){
     RetrofitClient.apiInstance.User().enqueue(object : Callback<UserResponse>{

         override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
             if (response.isSuccessful) {
                 val users = response.body()?.items
                 if (users != null) {
                     listUser.postValue(users)

                 }
             } else {
                 // Handle unsuccessful response
                 Log.e("API", "Error: ${response.code()}")
             }

         }

         override fun onFailure(call: Call<UserResponse>, t: Throwable) {
             Log.e("API", "Error: ${t.message}")
         }

     })
 }
    fun setSearchUser(query: String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("MainActivity", "Failed to get users", t)
                }
            })

    }

    fun getSearchUser(): LiveData<ArrayList<User>>{
        return listUser
    }
}