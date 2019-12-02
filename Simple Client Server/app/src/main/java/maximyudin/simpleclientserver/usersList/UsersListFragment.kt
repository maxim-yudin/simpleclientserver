package maximyudin.simpleclientserver.usersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import maximyudin.simpleclientserver.databinding.FragmentUsersListBinding

class UsersListFragment : Fragment() {
    private val viewModel: UsersListViewModel by lazy {
        ViewModelProviders.of(this).get(UsersListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUsersListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.usersList.adapter = UsersListAdapter(UsersListAdapter.OnClickListener {
            viewModel.displayUserDetails(it)
        })

        viewModel.navigateToSelectedUser.observe(this, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(UsersListFragmentDirections.actionShowUserDetail(it))
                viewModel.displayUserDetailsComplete()
            }
        })

        return binding.root
    }
}
