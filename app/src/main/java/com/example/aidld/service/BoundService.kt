package com.example.aidld.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import com.example.aidld.MyImplementor

/**
 *
 * create time 2022/5/25 10:26 上午
 * create by 胡汉君
 * 用于绑定
 */
class BoundService : Service() {
    private val TAG: String = "BoundService"
    private var binder: Binder = MyImplementor()
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    //在service中执行一个handler，一直进行更新，然后通知页面也进行更新，如何将这种变化通知给activity
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        //拿到loop自己创建handler

    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(TAG, "onStart startId $startId")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand startId $startId")
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind")

    }

    //创建一个binder,内部类
    inner class BoundBind : Binder() {
        fun getService(): Service {
            //返回boundService
            return this@BoundService
        }
    }


}