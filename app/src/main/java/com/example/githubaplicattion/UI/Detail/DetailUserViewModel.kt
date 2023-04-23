package com.example.githubaplicattion.UI.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubaplicattion.API.RetrofitClient
import com.example.githubaplicattion.Data.Model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel:ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()
    fun setUserDetail(username: String){
        username?.let {
            RetrofitClient.apiInstance
                .getUserDetail(it)
                .enqueue(object:Callback<DetailUserResponse>{
                    override fun onResponse(
                        call: Call<DetailUserResponse>,
                        response: Response<DetailUserResponse>
                    ) {
                        if(response.isSuccessful){
                            user.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                        Log.d("MainActivity", "Failed to get users", t)
                    }

                })
        }
    }
    fun getUserDetail():LiveData<DetailUserResponse>{
        return user
    }
}