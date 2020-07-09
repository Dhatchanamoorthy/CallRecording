package com.example.hubinorecording

import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import androidx.room.Room
import com.example.hubinorecording.Model.CallRecordDatabase
import com.example.hubinorecording.Model.RecordDao
import com.example.hubinorecording.Model.RecordData
import java.io.File
import java.util.*
import android.os.AsyncTask
import java.text.SimpleDateFormat


object CallRecorder {
    private var mRecorder: MediaRecorder? = null
    private var audioFile: File? = null
    var isRecording = false




    fun startRecorder() {
        if (isRecording){
            return
        } else isRecording = true

        try {
            mRecorder = MediaRecorder().apply {
                // if (number.isNullOrBlank()) return
                audioFile = createAudioFile("")
                Log.d("Audio recording started", audioFile?.name)

                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(audioFile?.absolutePath)
                prepare()
                start()
            }
        } catch (e: Exception) {
            Log.e("error", e.toString())
        }
    }


    fun stopRecording(number: String?) {
        if (!isRecording){
            return
        } else isRecording = false

        Log.d("record", "Stop Recording")

        mRecorder?.apply {
            try {
                stop()
                release()

                val database = CallRecordDatabase.instance
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                val timestamp = Calendar.getInstance().timeInMillis.toString()
                val recordData = RecordData(number, currentDate, getPath())
                AsyncTask.execute {
                    // Insert Data
                    database.RecordDao().insertData(recordData)
                }

            } catch (e: Exception) {
                Log.e("error", e.toString())
            }
        }
        mRecorder = null
    }

    private fun getPath(): String? {
        return audioFile?.path
    }

    private fun createAudioFile(number: String?): File? {
        ///  val date: String = formatTime(Date().time, "yyyy-MM-dd")
        val timestamp = Calendar.getInstance().timeInMillis
        return getStorageDir()?.let {
            File(it + "REC_" + number + "_" + "_" + timestamp + ".3gp")
        }
    }


    fun getStorageDir(): String? {
        val storageDir = App.appContext.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        if (storageDir?.exists() == false) storageDir.mkdirs()
        return storageDir?.let { it.absolutePath + File.separator }
    }

}