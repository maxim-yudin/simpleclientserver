package maximyudin.simpleclientserver

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import maximyudin.simpleclientserver.network.User
import maximyudin.simpleclientserver.usersList.UsersApiStatus
import maximyudin.simpleclientserver.usersList.UsersListAdapter

@BindingAdapter("usersList")
fun bindUsersListRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as UsersListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imgUri = imageUrl.toUri()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("usersApiStatus")
fun bindStatus(statusImageView: ImageView, status: UsersApiStatus?) {
    when (status) {
        UsersApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_loading_animation)
        }
        UsersApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UsersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}