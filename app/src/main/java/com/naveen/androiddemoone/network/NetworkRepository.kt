package com.naveen.naveenapp.network

class NetworkRepository : BaseRepository() {
    suspend fun getGithubUserSearch(userName: String){
        userListLiveDataList.postValue(ResultWrapper.loading())
        val response = NetworkClient.apiInterface.getGitHubUser(userName).await()
        if(response.isSuccessful){
            userList = response.body()
            userListLiveDataList.postValue(ResultWrapper.success(response.body()))
        }else{
            userListLiveDataList.postValue(ResultWrapper.error("Something went wrong"))
        }
    }

    suspend fun getGithubUserInfo(userName: String){
        userCompleteInfoLiveDataList.postValue(ResultWrapper.loading())
        val response = NetworkClient.apiInterface.getGitHubUserInfo(userName).await()
        if(response.isSuccessful){
            userCompleteInfo = response.body()
            userCompleteInfoLiveDataList.postValue(ResultWrapper.success(response.body()))
        }else{
            userCompleteInfoLiveDataList.postValue(ResultWrapper.error("Something went wrong : "))
        }
    }


}