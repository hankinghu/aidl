package com.example.aidld

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aidld.service.BackgroundService
import com.example.aidld.service.BoundService
import kotlinx.android.synthetic.main.activity_launch.*

/**
 *
 * create time 2022/5/14 11:06 下午
 * create by 胡汉君
 */
class LaunchActivity : AppCompatActivity() {
    private val TAG: String = "LaunchActivity"
    private lateinit var connect: ServiceConnection
    private var aidlInterface: IMyAidlInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        createConnect()
    }

    /***
     * 创建connect
     */
    private fun createConnect() {
        connect = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                //connect
                aidlInterface = IMyAidlInterface.Stub.asInterface(service)
                Log.d(TAG, "onServiceConnected ${aidlInterface?.message}")
                slogin.text = aidlInterface?.message

            }

            override fun onServiceDisconnected(name: ComponentName?) {
                //disconnect
                Log.d(TAG, "onServiceDisconnected")
                aidlInterface = null

            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, BoundService::class.java).also {
            applicationContext.bindService(it, connect, Context.BIND_AUTO_CREATE)
        }
        Log.d(TAG, "onStart")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        unbindService(connect)
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, BackgroundService::class.java)
        stopService(intent)
    }
}