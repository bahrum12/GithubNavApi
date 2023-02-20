package com.dicoding.githubnavapi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubnavapi.api.Client
import com.dicoding.githubnavapi.model.User
import com.dicoding.githubnavapi.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){
    private val _users = MutableLiveData<ArrayList<User>>()
    val users :LiveData<ArrayList<User>> get() =_users

    fun setUser(query: String) {
        Client.api
            .getUser(query)
            .enqueue(object: Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if(response.isSuccessful) {
                        _users.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }


}