package com.example.servicedemo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() { //ánh xạ code View và bắt sự kiện từ file xml tương ứng
    private var edtDataIntent : EditText? = null
    private var btnStartService : Button? = null
    private var btnStopService : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtDataIntent = findViewById(R.id.edt_data_intent)
        btnStartService = findViewById(R.id.btn_start_service)
        btnStopService = findViewById(R.id.btn_stop_service)

        btnStartService?.setOnClickListener{
            clickStartService()
        }

        btnStopService?.setOnClickListener{
            clickStopService()
        }
    }

    private fun clickStartService() {
        var intent = Intent(this,MyService::class.java)
        intent.putExtra("key_data_intent", edtDataIntent?.text.toString().trim())// trim() để xóa dấu trắng ở đầu và cuối
        // sau khi put data vào intent thì bên nhận là MyService phải có cơ chế nhận data trong intent => chạy sang file MyService=> hàm onStartCommand
        startService(intent)
    }

    private fun clickStopService() {
        var intent = Intent(this,MyService::class.java)
        stopService(intent )
    }

}