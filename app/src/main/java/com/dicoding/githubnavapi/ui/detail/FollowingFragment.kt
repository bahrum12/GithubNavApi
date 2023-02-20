package com.dicoding.githubnavapi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubnavapi.R
import com.dicoding.githubnavapi.adapter.UserAdapter
import com.dicoding.githubnavapi.databinding.FragmentFollowingBinding

class FollowingFragment(var username : String) : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel
    private lateinit var userAdapter : UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.username = username
        viewModel.setFollowing()
        showRecyclerView()
    }

    private fun showRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(binding.root.context)
        viewModel.followings.observe(viewLifecycleOwner, {
            userAdapter = UserAdapter(it)
            userAdapter.notifyDataSetChanged()
            binding.rvUser.adapter = userAdapter
            showProgress(false)
        })
    }

    private fun showProgress(state: Boolean) {
        binding.progressBar.isVisible = state
    }
}