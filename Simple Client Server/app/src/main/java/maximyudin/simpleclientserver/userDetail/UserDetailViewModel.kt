package maximyudin.simpleclientserver.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import maximyudin.simpleclientserver.network.User

class UserDetailViewModel(user: User) : ViewModel() {
    private val _selectedUser = MutableLiveData<User>()

    val selectedUser: LiveData<User>
        get() = _selectedUser

    init {
        _selectedUser.value = user
    }

    val displayUserFullName = Transformations.map(selectedUser) {
        "${it.firstName} ${it.lastName}"
    }
}