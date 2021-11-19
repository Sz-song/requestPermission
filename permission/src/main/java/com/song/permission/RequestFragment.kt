package com.song.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.song.permission.callback.PermissionsCallback
import com.song.permission.dialog.DeniedDialog

class RequestFragment : Fragment() {

    var multiPermissionCallback: PermissionsCallback? = null
    private val grantedList: MutableList<String> = mutableListOf()
    private val deniedList: MutableList<String> = mutableListOf()
    var deniedDialog: DeniedDialog? = null

    //去设置页面的回调
    private val settingPermissionsLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        for (index in (deniedList.size - 1) downTo 0) {
            if (ContextCompat.checkSelfPermission(requireActivity(), deniedList[index]) == PackageManager.PERMISSION_GRANTED) {
                grantedList.add(deniedList[index])
                deniedList.removeAt(index)
            }
        }
        multiPermissionCallback?.onResult(deniedList.size == 0, grantedList, deniedList)
    }
    //权限请求的回调
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
            if(deniedList.size>0){
                deniedDialog?.show()
                deniedDialog?.getCancelView()?.setOnClickListener {
                    deniedDialog?.dismiss()
                }
                deniedDialog?.getConfirmView()?.setOnClickListener {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    intent.data = Uri.parse("package:" + requireActivity().packageName)
                    settingPermissionsLauncher.launch(intent)
                    deniedDialog?.dismiss()
                }
            }
            multiPermissionCallback?.onResult(deniedList.size == 0, grantedList, deniedList)
        }


    fun request(
        permissions: Array<String>,
        callback: PermissionsCallback?,
        deniedDialog: DeniedDialog?
    ) {
        this.multiPermissionCallback = callback
        this.deniedDialog = deniedDialog
        multiPermissionsLauncher.launch(permissions)
    }

}