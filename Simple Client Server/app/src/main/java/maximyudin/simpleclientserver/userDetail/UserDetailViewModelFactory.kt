package maximyudin.simpleclientserver.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import maximyudin.simpleclientserver.network.User

class UserDetailViewModelFactory(private val user: User) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}