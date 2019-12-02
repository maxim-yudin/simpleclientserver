package maximyudin.simpleclientserver.usersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import maximyudin.simpleclientserver.network.User
import maximyudin.simpleclientserver.network.UsersApi

enum class UsersApiStatus { LOADING, ERROR, DONE }

class UsersListViewModel : ViewModel() {
    private var startPage = 1

    private val _status = MutableLiveData<UsersApiStatus>()
    val status: LiveData<UsersApiStatus>
        get() = _status

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _navigateToSelectedUser = MutableLiveData<User>()
    val navigateToSelectedUser: LiveData<User>
        get() = _navigateToSelectedUser

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUsers(startPage)
    }

    private fun getUsers(page: Int) {
        coroutineScope.launch {
            val getUsersDeferred = UsersApi.service.getUsersAsync(page)
            try {
                _status.value = UsersApiStatus.LOADING
                val usersResponse = getUsersDeferred.await()
                _status.value = UsersApiStatus.DONE
                _users.value = usersResponse.users
            } catch (e: Exception) {
                _status.value = UsersApiStatus.ERROR
                _users.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayUserDetails(user: User) {
        _navigateToSelectedUser.value = user
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null
    }
}