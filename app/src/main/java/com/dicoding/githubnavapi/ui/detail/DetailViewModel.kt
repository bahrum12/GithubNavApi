package com.dicoding.githubnavapi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubnavapi.api.Client
import com.dicoding.githubnavapi.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    private val _followers = MutableLiveData<ArrayList<User>>()
    val followers : MutableLiveData<ArrayList<User>> = _followers

    private val _followings = MutableLiveData<ArrayList<User>>()
    val followings : MutableLiveData<ArrayList<User>> = _followings

    var username: String = "default"
        get() = field
        set(value) {field = value}

    fun setDetailUser() {
        Client.api
            .getDetailUser(username!!)
            .enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful) {
                        _user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun setFollower() {
        Client.api
            .getFollower(username!!)
            .enqueue(object: Callback<ArrayList<User>> {
                override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                    if(response.isSuccessful) {
                        _followers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun setFollowing() {
        Client.api
            .getFollowing(username!!)
            .enqueue(object: Callback<ArrayList<User>> {
                override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                    if(response.isSuccessful) {
                        _followings.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }
}