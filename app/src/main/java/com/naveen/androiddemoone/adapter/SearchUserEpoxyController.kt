package com.naveen.naveenapp.adapter

import com.airbnb.epoxy.EpoxyController
import com.naveen.androiddemoone.serachUserRow
import com.naveen.naveenapp.network.UserSearchInfo

class SearchUserEpoxyController : EpoxyController(){

    var searchUserResult : List<UserSearchInfo> = emptyList()
    lateinit var onRowClick : RowClickListener

    override fun buildModels() {
        searchUserResult.forEachIndexed { index, search ->
            serachUserRow {
                id("id", index.toString())
                title(search.login)
                userImageURL(search.avatar_url)
                rowIndex(index)
                onRowClickListener(onRowClick)
            }
        }
    }
}

interface RowClickListener{
    fun onRowClick(itemPosition : Int)
}