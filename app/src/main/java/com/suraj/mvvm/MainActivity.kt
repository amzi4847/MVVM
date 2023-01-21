package com.suraj.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suraj.mvvm.databinding.ActivityMainBinding
import com.suraj.mvvm.db.Subscriber
import com.suraj.mvvm.db.subscriber_dao
import com.suraj.mvvm.db.subscriber_database
import com.suraj.mvvm.db.subscriber_repo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewmodel: SubscriberViewModel
    private lateinit var adapter:MyRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao:subscriber_dao = subscriber_database.getInstance(applicationContext).subscriber_dao
        val repo = subscriber_repo(dao)
        val factory = SubscriberViewModelFactory(repo)
        subscriberViewmodel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.vmSubscribers = subscriberViewmodel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView(){

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        adapter =  MyRecyclerView() { selectedItem: Subscriber ->
            listItemClicked(
                selectedItem
            )
        }
        binding.rvMain.adapter = adapter
        displayData()


    }

    private fun displayData(){
        subscriberViewmodel.subscribers.observe(this, Observer {
            Log.d("MyList",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber:Subscriber){
        Toast.makeText(this,subscriber.name,Toast.LENGTH_LONG).show()
        subscriberViewmodel.initUpdateOrDelete(subscriber)

    }
}