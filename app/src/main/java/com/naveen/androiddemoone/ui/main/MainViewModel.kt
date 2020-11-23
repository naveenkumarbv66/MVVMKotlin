package com.naveen.androiddemoone.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.naveen.naveenapp.network.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    private val _searchGithubUserList = MutableLiveData<GithubUserSearch>()
    val searchGithubUserList: LiveData<GithubUserSearch> = _searchGithubUserList

    private val _githubUserInfoList = MutableLiveData<UserCompleteInfo>()
    val githubUserInfoList: LiveData<UserCompleteInfo> = _githubUserInfoList

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage
    
    private val _isLoadingState = MutableLiveData(false)
    val isLoadingState : LiveData<Boolean> = _isLoadingState

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    val repository : NetworkRepository = NetworkRepository()


    fun cancelAllRequests() = coroutineContext.cancel()

    private val getSearchGithubUserListObserver = Observer<ResultWrapper<GithubUserSearch>>{ result ->
        when(result?.status){
            Status.LOADING ->{
                _isLoadingState.value = true
            }
            Status.ERROR ->{
                _isLoadingState.value = false
                _toastMessage.value = "Error in API response"
            }
            Status.SUCCESS ->{
                _searchGithubUserList.value = repository.userList
                _isLoadingState.value = false
            }
        }
    }

    fun initSearchObserver(moveName: String){
        repository.userListLiveDataList.removeObserver(getSearchGithubUserListObserver)

        repository.userListLiveDataList.value = null

        repository.userListLiveDataList.observeForever(getSearchGithubUserListObserver)

        scope.launch {
            try {
                repository.getGithubUserSearch(moveName)
            } catch (t: Throwable) {
            }
        }
    }

    //------------- User info logic ----------------------
    fun initUserInfoObserver(moveName: String){
        repository.userCompleteInfoLiveDataList.removeObserver(getUserInfoListObserver)

        repository.userCompleteInfoLiveDataList.value = null

        repository.userCompleteInfoLiveDataList.observeForever(getUserInfoListObserver)

        scope.launch {
            try {
                repository.getGithubUserInfo(moveName)
            } catch (t: Throwable) {
            }
        }
    }

    private val getUserInfoListObserver = Observer<ResultWrapper<UserCompleteInfo>>{ result ->
        when(result?.status){
            Status.LOADING ->{
                _isLoadingState.value = true
            }
            Status.ERROR ->{
                _isLoadingState.value = false
                _toastMessage.value = "Error in API response"
            }
            Status.SUCCESS ->{
                _githubUserInfoList.value = repository.userCompleteInfo
                _isLoadingState.value = false
            }
        }
    }
}