package com.song.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.song.permission.RequestPermission
import com.song.permission.callback.PermissionsCallback
import com.song.app.databinding.FragmentPermissionBinding

class PermissionFragment:Fragment() {

    private var binding: FragmentPermissionBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPermissionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.singlePermission?.setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.CAMERA)
                .addBeforeDialog()
                .requestCallback(object : PermissionsCallback {
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            Toast.makeText(requireContext(),"所有权限都已同意",Toast.LENGTH_SHORT).show()
                        }else{
                            val stringBuffer=StringBuffer()
                            deniedList.forEach{
                                stringBuffer.append(it)
                            }
                            Toast.makeText(requireContext(),stringBuffer.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }).request()
        }

        binding?.deniedDialogPermissions?.setOnClickListener {
            RequestPermission(this)
                .permission(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.CALL_PHONE)
                .addBeforeDialog()
                .addDeniedDialog()
                .requestCallback(object : PermissionsCallback {
                    override fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>) {
                        if (allGranted) {
                            Toast.makeText(requireContext(),"所有权限都已同意",Toast.LENGTH_SHORT).show()
                        }else{
                            val stringBuffer=StringBuffer()
                            deniedList.forEach{
                                stringBuffer.append(it)
                            }
                            Toast.makeText(requireContext(),stringBuffer.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }).request()
        }
    }
}