package com.song.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.single_permission).setOnClickListener {
            RequestPermission(this).request(android.Manifest.permission.CAMERA)
        }
        findViewById<TextView>(R.id.multi_permissions).setOnClickListener {

        }

    }
}