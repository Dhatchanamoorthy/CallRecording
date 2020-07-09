package com.example.hubinorecording.ui.ui

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hubinorecording.Model.RecordData
import com.example.hubinorecording.R
import kotlinx.android.synthetic.main.recycler_list.view.*

class CallAdapter(val items :ArrayList<RecordData>, val context: Context) : RecyclerView.Adapter<CallAdapter.ViewHolder>() {
    var mediaPlayer : MediaPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder(
         LayoutInflater.from(context).inflate(
             R.layout.recycler_list,
             parent,
             false
         )
     )
    }

    override fun getItemCount(): Int {
    return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.audio.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, Uri.parse(items.get(position).url))
            mediaPlayer?.start()
        }

        holder.number.text = items.get(position).number
        holder.time.text = items.get(position).time
       // holder.url.text = items.get(position).url
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
          val number = view.textViewNumber
          val time = view.textViewTime
          val audio = view.img_play
    }
}