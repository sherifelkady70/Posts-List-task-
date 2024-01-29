package com.route.task_implemented

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.task_implemented.databinding.RvItemsBinding
import com.route.task_implemented.models.Posts

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostsViewHolder>() {
    lateinit var postsList : ArrayList<Posts>

    fun setPostsList(list : ArrayList<Posts>){
        this.postsList=list
        notifyDataSetChanged()
    }

    class PostsViewHolder(val binding : RvItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
       val v = LayoutInflater.from(parent.context)
        return PostsViewHolder(RvItemsBinding.inflate(v,parent,false))
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val data : Posts = postsList[position]
        holder.binding.postIdPlace.text = data.id.toString()
    }
}