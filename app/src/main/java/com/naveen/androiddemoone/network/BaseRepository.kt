package com.naveen.naveenapp.network

import androidx.lifecycle.MutableLiveData

open class BaseRepository {
    val userListLiveDataList = MutableLiveData<ResultWrapper<GithubUserSearch>>()
    var userList : GithubUserSearch? = null

    var selectedUserBasicInfo : UserSearchInfo? = null

    val userCompleteInfoLiveDataList = MutableLiveData<ResultWrapper<UserCompleteInfo>>()
    var userCompleteInfo : UserCompleteInfo? = null

}