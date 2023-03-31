package com.rie.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rie.githubuser.databinding.ItemRowUserBinding
import com.rie.githubuser.response.ItemsSearch

class ListSearchAdapter (private val listUser : ArrayList<ItemsSearch>): RecyclerView.Adapter<ListSearchAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, avatar) = listUser[position]
        holder.binding.tvItemUsername.text = username
        Glide.with(holder.binding.imgItemPhoto.context)
            .load(avatar)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int =listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsSearch)
    }
}