package com.tech.rectest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    var my_recycler_view:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_recycler_view=findViewById(R.id.my_recycler_view)

        var status =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)


        if (status == PackageManager.PERMISSION_GRANTED) {
            getData()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE),
                22
            )
        }

    }

    //getting data from internal storage(permission)
    private fun getData() {

        var path ="/storage/emulated/0/DCIM/camera/"
        var f = File(path)
        if (!f.exists()) {
            path ="/storage/SdCard0/DCIM/camera/"
            f = File(path)
        }
        var files_list = f.listFiles()  // data is available here in the form of array of files


        //stpe 5: add the layout manager

        my_recycler_view?.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //activity is sending data to recycler View from here
        var myAdapter=MyAdapter(this,files_list)

        //set Adapter
        my_recycler_view?.adapter=myAdapter

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getData()
        } else {
            Toast.makeText(this, "you do not have permission", Toast.LENGTH_LONG).show()
        }
    }

}