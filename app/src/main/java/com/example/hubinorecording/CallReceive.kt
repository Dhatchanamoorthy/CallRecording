package com.example.hubinorecording

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.hubinorecording.Model.CallRecordDatabase


class CallReceive : BroadcastReceiver() {
    private var lastState = TelephonyManager.CALL_STATE_IDLE
    var isRecording = false

    override fun onReceive(context: Context?, intent: Intent?) {




        if (intent == null || intent.extras == null) {
            Log.e("CAll receiver", "Invalid onReceive bundle")
            return
        }

        if (intent.extras != null) {
            val mblNum = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            Log.d("phone 2", "" + mblNum)

            val stateStr = if (intent.hasExtra(TelephonyManager.EXTRA_STATE)) intent.getStringExtra(
                TelephonyManager.EXTRA_STATE
            ) else null

            var state = 0
            if (stateStr != null && stateStr == TelephonyManager.EXTRA_STATE_IDLE) {
                state = TelephonyManager.CALL_STATE_IDLE
            } else if (stateStr != null && stateStr == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                state = TelephonyManager.CALL_STATE_OFFHOOK
            } else if (stateStr != null && stateStr == TelephonyManager.EXTRA_STATE_RINGING) {
                state = TelephonyManager.CALL_STATE_RINGING
            }

            if (!mblNum.isNullOrEmpty()){
                onCallStateChanged(state, mblNum)
            }

        }

    }

    private fun onCallStateChanged(state: Int, number: String?) {
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                Log.d("CALL_STATE_RINGING : %s", number)

            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                Log.d("CALL_STATE_OFF_HOOK", number)
                Log.d("isrecord inside hook",""+isRecording)

                    CallRecorder.startRecorder()
                    isRecording = true
                    Log.d("isrecord",""+isRecording)


            }
            TelephonyManager.CALL_STATE_IDLE -> {
                Log.d("isrecord inside idle",""+isRecording)
                Log.d("CALL_STATE_IDLE", number)

                    Log.d("isrecord inside",""+isRecording)
                    CallRecorder.stopRecording(number)


            }
        }

        lastState = state

    }
}