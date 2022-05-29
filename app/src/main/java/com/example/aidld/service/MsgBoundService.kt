package com.example.aidld.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

/**
 *
 * create time 2022/5/29 4:54 下午
 * create by 胡汉君
 * 通过Messenger来进行通信
 */
const val MSG_HELLO = 1

class MsgBoundService : Service() {

    private lateinit var messager: Messenger
    override fun onBind(intent: Intent?): IBinder? {
        messager = Messenger(MsgHandler(this))
        return messager.binder
    }

    internal class MsgHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_HELLO -> {
                    Toast.makeText(applicationContext, "hello world", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}