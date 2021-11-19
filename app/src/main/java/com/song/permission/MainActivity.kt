package com.song.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.song.permission.callback.PermissionsCallback


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        findViewById<TextView>(R.id.single_permission).setOnClickListener {
            /**
             * @param this  FragmentActivity or Fragment
             **/
            RequestPermission(this)
                .permission(android.Manifest.permission.CAMERA,android.Manifest.permission.CALL_PHONE)
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
                })

        }
    }
}