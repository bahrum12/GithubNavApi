package com.dicoding.githubnavapi.ui.main

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubnavapi.R
import com.dicoding.githubnavapi.adapter.UserAdapter
import com.dicoding.githubnavapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter : UserAdapter
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.layoutManager = LinearLayoutManager(this)

        viewModel.users.observe(this, {
            Log.d("MAIN", it.isEmpty().toString())
            if(it.isEmpty()) {
                binding.noResult.isVisible = true
            } else {
                binding.noResult.isVisible = false
            }
            userAdapter = UserAdapter(it)
            userAdapter.notifyDataSetChanged()
            binding.rvUser.adapter = userAdapter
            showProgress(false)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_bar_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Type username here"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    showProgress(false)
                }else{
                    showProgress(true)
                    viewModel.setUser(query)
                }
                return true
            }

            override fun onQueryTextChange(action: String): Boolean {

                return false
            }
        })
        return true
    }

    private fun showProgress(state: Boolean) {
        binding.progressBar.isVisible = state
    }

}