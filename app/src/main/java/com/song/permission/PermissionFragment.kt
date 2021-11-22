package com.song.permission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.song.permission.callback.PermissionsCallback
import com.song.permission.dialog.MyDeniedDialog

class PermissionFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_permission, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.single_permission).setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.CAMERA)
                .requestCallback(object : PermissionsCallback {
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            Toast.makeText(context,"所有权限都已同意", Toast.LENGTH_SHORT).show()
                        }else{
                            val stringBuffer=StringBuffer()
                            deniedList.forEach{
                                stringBuffer.append(it)
                            }
                            Toast.makeText(context,stringBuffer.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }).request()
        }

        view.findViewById<TextView>(R.id.denied_dialog_permissions).setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.CALL_PHONE)
                .addDeniedDialog()
                .requestCallback(object : PermissionsCallback {
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            Toast.makeText(requireContext(),"所有权限都已同意", Toast.LENGTH_SHORT).show()
                        }else{
                            val stringBuffer=StringBuffer()
                            deniedList.forEach{
                                stringBuffer.append(it)
                            }
                            Toast.makeText(requireContext(),stringBuffer.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }).request()
        }
    }
}