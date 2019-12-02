package maximyudin.simpleclientserver.userDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import maximyudin.simpleclientserver.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val user = UserDetailFragmentArgs.fromBundle(arguments!!).selectedUser
        val viewModelFactory = UserDetailViewModelFactory(user)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserDetailViewModel::class.java)
        return binding.root
    }
}