package com.song.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.song.permission.callback.PermissionsCallback
import com.song.permission.dialog.MyDeniedDialog


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.single_permission).setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.CAMERA)
                .requestCallback(object : PermissionsCallback {
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
                        }else{
                            val stringBuffer=StringBuffer()
                            deniedList.forEach{
                                stringBuffer.append(it)
                            }
                            Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }).request()
        }

        findViewById<TextView>(R.id.denied_dialog_permissions).setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.CALL_PHONE)
                .addDeniedDialog()
                .requestCallback(object : PermissionsCallback {
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            Toast.makeText(this@MainActivity,"所有权限都已同意",Toast.LENGTH_SHORT).show()
                        }else{
                            val stringBuffer=StringBuffer()
                            deniedList.forEach{
                                stringBuffer.append(it)
                            }
                            Toast.makeText(this@MainActivity,stringBuffer.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }).request()
        }

    }
}