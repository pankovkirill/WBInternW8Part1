package com.example.wbinternw8part1.view.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.wbinternw8part1.model.DataModel

class DiffUtilsCallBack(
    private val oldList: List<DataModel>,
    private val newList: List<DataModel>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == oldList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}