package com.suraj.mvvm

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suraj.mvvm.db.Subscriber
import com.suraj.mvvm.db.subscriber_repo
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repo_sub: subscriber_repo) : ViewModel(), Observable {

    val subscribers = repo_sub.subscriber
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdate: Subscriber

    @Bindable
    var sub_name = MutableLiveData<String>()

    @Bindable
    var sub_email = MutableLiveData<String>()

    @Bindable
    var sub_btn_save = MutableLiveData<String>()

    @Bindable
    var sub_btn_clear = MutableLiveData<String>()

    @Bindable
    var sub_btn_clearAll = MutableLiveData<String>()

    init {
        sub_btn_clear.value = "CLEAR"
        sub_btn_clearAll.value = "CLEAR ALL"
        sub_btn_save.value = "SAVE"
    }

    fun initUpdateOrDelete(subscriber: Subscriber) {
        sub_name.value = subscriber.name
        sub_email.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdate = subscriber
        sub_btn_save.value = "UPDATE"
        sub_btn_clearAll.value = "DELETE"

    }

    fun saveOrUpdate() {

        if (isUpdateOrDelete) {
            subscriberToUpdate.name = sub_name.value!!
            subscriberToUpdate.email = sub_email.value!!
            update(subscriberToUpdate)
        } else {
            insert(Subscriber(0, sub_name.value!!, sub_email.value!!))
            sub_name.value = null
            sub_email.value = null

        }
    }

    fun clearOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdate)
        } else {
            deleteAll()
        }
    }


    fun insert(subscriber: Subscriber) {
        viewModelScope.launch {
            repo_sub.insertSubscriber(subscriber)
        }
    }

    fun update(subscriber: Subscriber) {
        viewModelScope.launch {
            repo_sub.updateSubscriber(subscriber)
            sub_name.value = null
            sub_email.value = null
            isUpdateOrDelete = false
            sub_btn_save.value = "SAVE"
            sub_btn_clearAll.value = "CLEAR ALL"
        }
    }

    fun delete(subscriber: Subscriber) {
        viewModelScope.launch {
            repo_sub.deleta(subscriber)
            sub_name.value = null
            sub_email.value = null
            isUpdateOrDelete = false
            sub_btn_save.value = "SAVE"
            sub_btn_clearAll.value = "CLEAR ALL"
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repo_sub.deleteAll()

        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}