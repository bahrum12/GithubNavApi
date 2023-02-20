package com.dicoding.githubnavapi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.githubnavapi.R
import com.dicoding.githubnavapi.adapter.SectionPagerAdapter
import com.dicoding.githubnavapi.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewPager : ViewPager2
    private lateinit var tabs : TabLayout

    private val viewModel : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()

        viewModel.username = intent.getStringExtra("USERNAME")!!
        setViewPager()
        viewModel.setDetailUser()
        setProfile()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail User"
        }
    }

    private fun setViewPager() {
        val pagerAdapter = SectionPagerAdapter(this, viewModel.username!!)

        binding.apply {
            viewPager = viewpager
            tabs = tablayout
        }

        viewPager.adapter = pagerAdapter
        val titles = resources.getStringArray(R.array.tab_menu)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    private fun setProfile() {
        viewModel.user.observe(this) {
            binding.apply {
                it.apply {
                    tvUsername.text = username
                    tvName.text = name
                    tvFollower.text = followers.toString() + " Followers"
                    tvFollowing.text = following.toString() + " Following"
                    tvRepository.text = publicRepos.toString() + " Repositories"

                    tvCompany.isVisible = if (company == null) false else true
                    tvCompany.text = company

                    tvLocation.isVisible = if (location == null) false else true
                    tvLocation.text = location

                    Glide.with(this@DetailActivity)
                        .load(avatarUrl)
                        .circleCrop()
                        .into(ivUser)
                }
            }

        }
    }
}