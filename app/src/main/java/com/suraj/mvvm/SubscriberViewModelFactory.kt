package com.suraj.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suraj.mvvm.db.subscriber_repo

class SubscriberViewModelFactory(private val repo:subscriber_repo):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repo) as T
        }
        throw IllegalArgumentException("class Error")
    }

}