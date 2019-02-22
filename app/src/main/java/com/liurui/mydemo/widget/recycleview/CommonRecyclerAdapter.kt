package com.ucicsh.NoraPlus.Express.view.adpter.recycleview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by LiuRui on 2018/11/27
 */
abstract class CommonRecyclerAdapter<T>(context: Context, mLayoutId: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context = context
    private var mLayoutId: Int? = null
    var mInflater: LayoutInflater? = null
    var mData: MutableList<T>
    var mItemClickListener: OnItemClickListener? = null

    init {
        this.mLayoutId = mLayoutId
        this.mInflater = LayoutInflater.from(context)
        mData = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 先inflate数据\
        var itemView = mInflater!!.inflate(mLayoutId!!, parent, false)
        itemView.setOnClickListener { v ->
            mItemClickListener?.apply {
                onItemClick(v)
            }
        }

        itemView.setOnLongClickListener { v ->
            mItemClickListener?.apply {
                onItemLongClick(v)
            }
            true
        }
        // 返回ViewHolder
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        convert(holder, mData[position])
    }

    abstract fun convert(holder: RecyclerView.ViewHolder, item: T)

    fun addData(datas: MutableList<T>) {
        mData.addAll(datas)
        notifyDataSetChanged()
    }

    fun addData(data: T) {
        mData.add(data)
        notifyDataSetChanged()
    }

    fun setData(datas: MutableList<T>){
        mData.clear()
        mData.addAll(datas)
        notifyDataSetChanged()
    }

    fun getData():MutableList<T>{
        return mData
    }

    fun clearData(){
        mData.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mItemClickListener = listener
    }
}

open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}

interface OnItemClickListener {
    fun onItemClick(view: View)
    fun onItemLongClick(view: View)
}