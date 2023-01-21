package com.suraj.mvvm.db

class subscriber_repo(private val dao: subscriber_dao) {


    val subscriber = dao.getSubscribers()

    suspend fun insertSubscriber(subscriber: Subscriber) {
        dao.insert_subscriber(subscriber)
    }

    suspend fun insertSubscriber(Subscribers: ArrayList<Subscriber>) {
        dao.insert_subscriber(Subscribers)
    }

    suspend fun updateSubscriber(subscriber: Subscriber) {
        dao.update_subscriber(subscriber)
    }

    suspend fun deleta(subscriber: Subscriber) {
        dao.delete_subscriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.delete_all()
    }

}