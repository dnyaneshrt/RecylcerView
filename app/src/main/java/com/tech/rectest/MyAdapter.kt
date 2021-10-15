package com.tech.rectest


import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

import java.io.File

//step 1: create a class as a child of RecyclerView.Adapter<MyViewHolder>
//step 3: provide implemenation to abstract methods.(present in RecyclerView.Adapter)

class MyAdapter(var mainActivity: MainActivity, var listfiles: Array<File>?) : RecyclerView.Adapter<MyAdapter.MyHolder>() {



        class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var circle_image = itemView.findViewById<CircleImageView>(R.id.circle_image)
            var txt_name = itemView.findViewById<TextView>(R.id.txt_name)
            var txt_size = itemView.findViewById<TextView>(R.id.txt_size)
            var button_delete = itemView.findViewById<ImageView>(R.id.btn_delete)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.my, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        var file = listfiles!![position]
        var bmp = BitmapFactory.decodeFile(file.path)
        var cmp = ThumbnailUtils.extractThumbnail(bmp, 110, 110)
        holder.circle_image.setImageBitmap(cmp)

        holder.txt_name.text = file.name
        holder.txt_size.text = file.length().toString()
        holder.button_delete.setOnClickListener {
            file.delete()
        }
    }

    override fun getItemCount(): Int {
        Log.d("count",listfiles!!.size.toString())
        return listfiles!!.size
    }
}

