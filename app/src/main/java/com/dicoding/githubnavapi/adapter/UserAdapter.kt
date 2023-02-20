package com.dicoding.githubnavapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubnavapi.databinding.ItemUserBinding
import com.dicoding.githubnavapi.model.User
import com.dicoding.githubnavapi.ui.detail.DetailActivity

class UserAdapter(private val users : ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.apply {
                with(user){
                    itemView.setOnClickListener{
                        val intent =Intent(itemView.context,DetailActivity::class.java)
                        intent.putExtra("USERNAME", username)
                        itemView.context.startActivity(intent)
                    }
                    Glide.with(itemView)
                        .load(avatarUrl)
                        .circleCrop()
                        .into(ivUser)
                    tvUsername.text = username
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : UserAdapter.ItemViewHolder{
        val view = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
       viewHolder.bind(users[position])
    }
    override fun getItemCount() = users.size
}