package com.tech.rectest

import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

//step 1: create a class as a child of RecyclerView.Adapter<MyViewHolder>
//step 3: provide implemenation to abstract methods.(present in RecyclerView.Adapter)

class MyAdapter(var mainActivity: MainActivity, var listfile: Array<File>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.my_view, parent, false)
        var Holder = MyViewHolder(view)
        return Holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var file_item = listfile[position]

        var btmp = BitmapFactory.decodeFile(file_item.path)
        var cmp_image = ThumbnailUtils.extractThumbnail(btmp, 50, 50)

        holder.circleImageView.setImageBitmap(cmp_image)
        
        holder.text_name.text = file_item.name
        holder.text_size.text = file_item.length().toString()
        holder.btn_delete.setOnClickListener {
            //use AlertDialog before deleting it.
            file_item.delete()
        }

    }

    override fun getItemCount(): Int {
        return listfile.size
    }
}


//Step 2: create a ViewHolder class as a child of  RecyclerView.ViewHolder(itemView)
//ste 4: collect your views inside ViewHolder
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var circleImageView = itemView.findViewById<CircleImageView>(R.id.circle_image)
    var text_name = itemView.findViewById<TextView>(R.id.txt_name)
    var text_size = itemView.findViewById<TextView>(R.id.txt_size)
    var btn_delete = itemView.findViewById<Button>(R.id.btn_delete)


}