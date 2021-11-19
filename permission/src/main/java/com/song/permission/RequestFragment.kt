package com.song.permission

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.song.permission.callback.PermissionsCallback

class RequestFragment : Fragment() {

    var multiPermissionCallback: PermissionsCallback? = null
    private val grantedList: MutableList<String> = mutableListOf()
    private val deniedList: MutableList<String> = mutableListOf()


    //多个权限请求的回调
    private val multiPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGrantedMap ->
            grantedList.clear()
            deniedList.clear()
            isGrantedMap.entries.forEach {
                if (it.value) {
                    grantedList.add(it.key)
                } else {
                    deniedList.add(it.key)
                }
            }
            multiPermissionCallback?.onResult(deniedList.size == 0, grantedList, deniedList)
        }


    fun request(permissions: Array<String>, callback : PermissionsCallback) {
        this.multiPermissionCallback = callback
        multiPermissionsLauncher.launch(permissions)
    }

}