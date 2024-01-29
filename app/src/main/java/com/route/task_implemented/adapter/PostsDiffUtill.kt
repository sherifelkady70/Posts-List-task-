package com.route.task_implemented.adapter

import androidx.recyclerview.widget.DiffUtil
import com.route.task_implemented.models.Posts

class PostsDiffUtill(
    val oldList : List<Posts> ,
    val newList: List<Posts>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}