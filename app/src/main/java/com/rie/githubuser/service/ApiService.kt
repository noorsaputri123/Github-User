package com.rie.githubuser.service

import com.rie.githubuser.response.ItemsSearch
import com.rie.githubuser.response.ResponseSearch
import com.rie.githubuser.response.ResponseSearchDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUsers(
        @Query("q") username: String
    ): Call<ResponseSearch>


    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<ResponseSearchDetail>


    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String?
    ): Call<List<ItemsSearch>>


    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String?
    ): Call<List<ItemsSearch>>
}