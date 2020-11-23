
package com.naveen.naveenapp.network
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.naveen.naveenapp.adapter.RowClickListener
import com.squareup.picasso.Picasso

data class GithubUserSearch (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<UserSearchInfo>
)

data class UserSearchInfo (
    val login : String = "",
    val id : Int,
    val node_id : String = "",
    val avatar_url : String = "",
    val gravatar_id : String = "",
    val url : String = "",
    val html_url : String = "",
    val followers_url : String = "",
    val following_url : String = "",
    val gists_url : String = "",
    val starred_url : String = "",
    val subscriptions_url : String = "",
    val organizations_url : String = "",
    val repos_url : String = "",
    val events_url : String = "",
    val received_events_url : String = "",
    val type : String = "",
    val site_admin : Boolean,
    val score : Int
)

data class UserCompleteInfo (
    val login : String = "",
    val id : Int,
    val node_id : String = "",
    val avatar_url : String = "",
    val gravatar_id : String = "",
    val url : String = "",
    val html_url : String = "",
    val followers_url : String = "",
    val following_url : String = "",
    val gists_url : String = "",
    val starred_url : String = "",
    val subscriptions_url : String = "",
    val organizations_url : String = "",
    val repos_url : String = "",
    val events_url : String = "",
    val received_events_url : String = "",
    val type : String = "",
    val site_admin : Boolean = false,
    val name : String = "",
    val company : String = "",
    val blog : String = "",
    val location : String = "",
    val email : String = "",
    val hireable : Boolean = false,
    val bio : String = "",
    val twitter_username : String = "",
    val public_repos : Int,
    val public_gists : Int,
    val followers : Int,
    val following : Int,
    val created_at : String = "",
    val updated_at : String = ""
){
    fun getFollowers() = followers.toString()

    fun getFollowing() = following.toString()
}

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}

data class ResultWrapper<out T>(val status: Status, val data: T?, val message: String?){
    companion object{
        fun <T> success(data: T?):ResultWrapper<T>{
            return ResultWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null):ResultWrapper<T>{
            return ResultWrapper(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?=null):ResultWrapper<T>{
            return ResultWrapper(Status.LOADING, data, null)
        }
    }
}

@BindingAdapter("imageURL")
fun setImage(view: ImageView, url: String){
    url.let {
        if(!it.isNullOrBlank()){
            Picasso.get().load(url).into(view)
        }
    }
}

@BindingAdapter("itemPosition", "onRowClick")
fun getRowClick(view: ConstraintLayout, itemPosition: Int, onRowClick: RowClickListener){
    view.setOnClickListener{
        onRowClick.onRowClick(itemPosition)
    }
}