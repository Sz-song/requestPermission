package com.song.permission

import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class RequestFragment:Fragment() {
    private val singlePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted){
                Toast.makeText(activity,"同意", Toast.LENGTH_SHORT).show()
            }
        }
    private val multiPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGrantedMap ->
            isGrantedMap.entries.forEach{
                Log.e(it.key,it.value.toString())
            }
        }
    fun request(permission :String){
        singlePermissionLauncher.launch(permission)
    }
}