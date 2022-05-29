package com.example.aidld

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.aidld.service.BoundService
import com.example.aidld.service.ForegroundsService
import com.example.aidld.service.MSG_HELLO
import com.example.aidld.service.MsgBoundService
import kotlinx.android.synthetic.main.activity_launch.*

/**
 *
 * create time 2022/5/14 11:06 下午
 * create by 胡汉君
 */
class LaunchActivity : AppCompatActivity() {
    private val TAG: String = "LaunchActivity"
    private lateinit var connect: ServiceConnection
    private lateinit var msgConnection: ServiceConnection
    private var messenger: Messenger? = null
    private var aidlInterface: IMyAidlInterface? = null
    private lateinit var boundIntent: Intent
    private lateinit var msgIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        boundIntent = Intent(this, BoundService::class.java)
        msgIntent = Intent(this, MsgBoundService::class.java)
        createConnect()
        startService.setOnClickListener {
            Log.d(TAG, "start service")
            startService(boundIntent)
        }
        bindService.setOnClickListener {
            Log.d(TAG, "bindService clicked")
            bindService()
        }
        unBindService.setOnClickListener {
            Log.d(TAG, "unbindService clicked")
            unBindService()
        }
        stopService.setOnClickListener {
            Log.d(TAG, "stopService clicked")
            stopService(boundIntent)
        }
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
        msgConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                messenger = Messenger(service)
                val msg = Message.obtain(null, MSG_HELLO, 0, 0)
                messenger?.send(msg)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                messenger = null
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        val foreSeIntent = Intent(this, ForegroundsService::class.java)
        applicationContext.startForegroundService(foreSeIntent)

    }

    private fun bindService() {
        applicationContext.bindService(boundIntent, connect, BIND_AUTO_CREATE)
        applicationContext.bindService(msgIntent, msgConnection, BIND_AUTO_CREATE)
    }

    private fun unBindService() {
        applicationContext.unbindService(connect)
        applicationContext.unbindService(msgConnection)

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
//        val intent = Intent(this, BackgroundService::class.java)
//        stopService(intent)
    }
}