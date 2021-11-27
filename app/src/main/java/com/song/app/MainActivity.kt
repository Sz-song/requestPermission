package com.song.app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.song.permission.RequestPermission
import com.song.permission.callback.PermissionsCallback
import com.song.app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.singlePermission.setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.BLUETOOTH)
                .addBeforeDialog()
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

        binding.deniedDialogPermissions.setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.CALL_PHONE)
                .addBeforeDialog()
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