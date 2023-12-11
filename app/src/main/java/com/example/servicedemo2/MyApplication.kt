package com.example.servicedemo2

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {
    val CHANNEL_ID : String = "channel_service_example"
    override fun onCreate() {
        super.onCreate()
        createChannelNotification()// từ android26 phải tạo channel id
    }

    private fun createChannelNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
           var channel : NotificationChannel = NotificationChannel(CHANNEL_ID,
                                            "Chanel Service Example",
                                                NotificationManager.IMPORTANCE_DEFAULT)
            var manager : NotificationManager = getSystemService((NotificationManager::class.java))
            // manager co the null
            if(manager != null){
                manager.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            if(instance == null){
                instance = MyApplication()
            }
            return instance!!
        }
    }

}