package com.example.hubinorecording.ui.ui

import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import android.Manifest.permission

import android.content.pm.PackageManager
import android.os.AsyncTask
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubinorecording.Model.CallRecordDatabase
import com.example.hubinorecording.Model.RecordData
import com.example.hubinorecording.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.collections.ArrayList







class HomeActivity : AppCompatActivity() {

    private var mRecorder: MediaRecorder? = null
    private var audioFile: File? = null
    private val RECORD_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        checkAndRequestPermission()


        recyclerview_tasks.layoutManager = LinearLayoutManager(this, androidx.recyclerview.widget.RecyclerView.VERTICAL, false)


        val database = CallRecordDatabase.instance
        AsyncTask.execute {
            val storedData = database.RecordDao().getRecordData()
            val storedUserData = database.UserDao().getUserData()
            Log.d("Stored data", storedData.toString())
            Log.d("Stored user data", storedUserData.toString())
            recyclerview_tasks.adapter = CallAdapter(
                storedData as ArrayList<RecordData>,
                this
            )

            if (storedData.isNullOrEmpty()){
                txt_no_records.visibility = View.VISIBLE
            } else
                txt_no_records.visibility = View.GONE
        }

    }

    private fun checkAndRequestPermission() {
      /*  val readPhoneState =
            ContextCompat.checkSelfPermission(this, READ_PHONE_STATE)
        val read_call_log =
            ContextCompat.checkSelfPermission(this, READ_CALL_LOG)*/

        val listPermissionsNeeded = ArrayList<String>()

        val PERMISSIONS = arrayOf(
            permission.RECORD_AUDIO,
            permission.CALL_PHONE,
            permission.READ_CALL_LOG,
            permission.READ_PHONE_STATE

        )


        if (!hasPermissions(*PERMISSIONS)) {
            ActivityCompat.requestPermissions(this,
                PERMISSIONS,
                RECORD_REQUEST_CODE)
        }




    }
    fun hasPermissions(vararg permissions: String): Boolean {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        return true
    }


    }
