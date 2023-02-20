package com.dicoding.githubnavapi.api

import com.dicoding.githubnavapi.model.User
import com.dicoding.githubnavapi.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<User>

    @GET("users/{username}/followers")
    fun getFollower(
        @Path("username") username: String
    ): Call<ArrayList<User>>


    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}