package com.dicoding.githubnavapi.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubnavapi.ui.detail.FollowerFragment
import com.dicoding.githubnavapi.ui.detail.FollowingFragment

class SectionPagerAdapter(activity: AppCompatActivity, var username : String) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int) : Fragment{
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment(username)
            1 -> fragment = FollowingFragment(username)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2

}