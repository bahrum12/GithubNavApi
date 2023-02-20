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
import com.dicoding.githubnavapi.databinding.FragmentFollowerBinding

class FollowerFragment(var username: String) : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel
    private lateinit var userAdapter : UserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.username = username
        viewModel.setFollower()
        showRecyclerView()
    }

    private fun showRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(binding.root.context)
        viewModel.followers.observe(viewLifecycleOwner, {
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