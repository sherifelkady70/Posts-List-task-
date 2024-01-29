package com.route.task_implemented.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.task_implemented.databinding.RvItemsBinding
import com.route.task_implemented.models.Posts

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostsViewHolder>() {
    private var postsList : ArrayList<Posts> = ArrayList()

    fun setList(postslist : ArrayList<Posts>){
        val diffResult = DiffUtil.calculateDiff(PostsDiffUtill(postsList,postslist))
        postsList = postslist
        diffResult.dispatchUpdatesTo(this)
//        postsList.clear()
//        postsList.addAll(postslist)
////        this.postsList=postslist
//        notifyDataSetChanged()
    }

    class PostsViewHolder(val binding : RvItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
       val v = LayoutInflater.from(parent.context)
        return PostsViewHolder(RvItemsBinding.inflate(v))
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val data : Posts = postsList[position]


        holder.binding.postIdPlace.text = data.id.toString()
        holder.itemView.setOnClickListener {
            onPostClick.let {
                onPostClick?.onClickListener(data,position)
            }
        }
    }

    var onPostClick : OnItemClickListener?=null
    interface OnItemClickListener{
        fun onClickListener(post:Posts , position: Int)
    }
}