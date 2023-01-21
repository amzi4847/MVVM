package com.suraj.mvvm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suraj.mvvm.databinding.ListItemBinding
import com.suraj.mvvm.db.Subscriber

class MyRecyclerView(private val functionLitsener: (Subscriber) -> Unit) : RecyclerView.Adapter<myViewHolder>() {

    private val subscriberList = ArrayList<Subscriber>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(LayoutInflater, R.layout.list_item, parent, false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(subscriberList[position],functionLitsener)

    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }

    fun setList(subscribers: List<Subscriber>){
        subscriberList.clear()
        subscriberList.addAll(subscribers)
    }


}

class myViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(subscriber: Subscriber, functionLitsener: (Subscriber) -> Unit) {
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            functionLitsener(subscriber)
          //  Log.d("MYTAG",subscriber.name)
        }
    }


}