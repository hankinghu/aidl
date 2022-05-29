package com.example.aidld.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/**
 *
 * create time 2022/5/24 2:49 下午
 * create by 胡汉君
 * 后台服务，module
 */
class BackgroundService : Service() {
    private var binder: Binder? = null

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(TAG,"onStart intent $intent startid $startId")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand intent $intent startid $startId  flags $flags")
        Toast.makeText(this,"this is toast", Toast.LENGTH_LONG).show()
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG,"onUnbind  intent $intent")

        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG,"onRebind  intent $intent")

    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d(TAG,"onTaskRemoved  intent $rootIntent")

    }

    companion object {
        private const val TAG = "backgroundService"
    }

}