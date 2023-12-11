package com.example.servicedemo2

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    override fun onCreate() {
        //The service is being created
        super.onCreate()
        Log.e("ServiceDemo", "myService onCreate")
    }

    // nhận intent và xử lý data trong intent
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //The service is starting, due to a call to startService()
        var strDataIntent : String? = intent?.getStringExtra("key_data_intent")// giá trị truyền vào phải đúng là cái key lúc activity gửi

        sendNotification(strDataIntent)
        Log.e("ServiceDemo", "myService onStartCommand")
        return START_NOT_STICKY
    }

    private fun sendNotification(strDataIntent: String?) {
        // bây giờ muốn click vào putNotification thì mở lại main actvity
        var intent : Intent = Intent(this, MainActivity::class.java)// intent này xác định nơi muốn nhảy vào
        var pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)// PendingIntent được sử dụng để đóng gói một Intent và cung cấp quyền truy cập không trực tiếp đến nó.
                                                                        // khi cần thực hiện một hành động (ví dụ: mở một Activity, gửi một Broadcast, kích hoạt một dịch vụ) tại một thời điểm sau, thường là từ một thành phần khác hoặc từ một tiến trình khác.
        var channelID = MyApplication.getInstance().CHANNEL_ID
        var notification : Notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Title notification service example")
            .setContentText(strDataIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        // gửi notification cho foreground Service
        startForeground(1, notification) // nếu comment dòng này thì service vẫn chạy ngầm và sau 1 khoảng thời gian thì service tự hủy chứ ko hoạt động vô thời hạn.
                                          // // nếu ko thì service chạy vô thời hạn
//        stopSelf() // tự dừng
        }

    override fun onBind(intent: Intent): IBinder? {// luôn phải triển khai, sử dụng nếu dùng Bound Service, còn ko thì return null
//        A client is binding to the service with bindService()
        Log.e("ServiceDemo", "myService onBind")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        // All clients have unbound with unbindService()
        Log.e("ServiceDemo", "myService onUnbind")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
        Log.e("ServiceDemo", "myService onRebind")
        super.onRebind(intent)
    }

    override fun onDestroy() {
        // The service is no longer used and is being destroyed
        super.onDestroy()
        Log.e("ServiceDemo", "myService onDestroy")
    }


}