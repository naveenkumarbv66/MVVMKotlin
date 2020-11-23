package com.naveen.naveenapp.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("search/users?")
    fun getGitHubUser(@Query(value = "q") userName: String): Deferred<Response<GithubUserSearch>>

    @GET("users/{userName}")
    fun getGitHubUserInfo(@Path(value = "userName") userName: String): Deferred<Response<UserCompleteInfo>>
}