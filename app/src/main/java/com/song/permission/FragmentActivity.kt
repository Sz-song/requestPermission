package com.song.permission

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.song.permission.callback.PermissionsCallback

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        findViewById<TextView>(R.id.single_permission).setOnClickListener {
            RequestPermission(this).request(android.Manifest.permission.CAMERA,
                object : PermissionCallback {
                    override fun onResult(isGranted: Boolean) {
                        if (isGranted) {

                        }
                    }
                })
        }
        findViewById<TextView>(R.id.multi_permissions).setOnClickListener {
            RequestPermission(this).request(
                object : PermissionsCallback {
                    override fun onResult(
                        allGranted: Boolean,
                        grantedList: List<String>,
                        deniedList: List<String>
                    ) {
                        //todo
                    }
                },android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.READ_CONTACTS)
        }

    }
}