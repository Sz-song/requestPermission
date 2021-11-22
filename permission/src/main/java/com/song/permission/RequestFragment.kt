package com.song.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.song.permission.callback.PermissionsCallback
import com.song.permission.dialog.PermissionDialog

class RequestFragment : Fragment() {

    var permissionCallback: PermissionsCallback? = null
    private val grantedList: MutableList<String> = mutableListOf()
    private val deniedList: MutableList<String> = mutableListOf()
    private var deniedDialog: PermissionDialog? = null
    private var beforeDialog: PermissionDialog? = null


    //去设置页面的回调
    private val settingLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            for (index in (deniedList.size - 1) downTo 0) {
                if (ContextCompat.checkSelfPermission(
                        requireActivity(),
                        deniedList[index]
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    grantedList.add(deniedList[index])
                    deniedList.removeAt(index)
                }
            }
            permissionCallback?.onResult(deniedList.size == 0, grantedList, deniedList)
        }

    //权限请求的回调
    private val permissionsLauncher =
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
            if (deniedList.size > 0) {
                deniedDialog?.deniedList?.clear()
                deniedDialog?.deniedList?.addAll(deniedList)
                deniedDialog?.show()
                deniedDialog?.getCancelView()?.setOnClickListener {
                    deniedDialog?.dismiss()
                    permissionCallback?.onResult(deniedList.size == 0, grantedList, deniedList)
                }
                deniedDialog?.getConfirmView()?.setOnClickListener {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    intent.data = Uri.parse("package:" + requireActivity().packageName)
                    settingLauncher.launch(intent)
                    deniedDialog?.dismiss()
                }
            } else {
                permissionCallback?.onResult(deniedList.size == 0, grantedList, deniedList)
            }
        }


    fun request(
        permissions: Array<String>,
        callback: PermissionsCallback?,
        deniedDialog: PermissionDialog?,
        beforeDialog: PermissionDialog?
    ) {
        this.permissionCallback = callback
        this.deniedDialog = deniedDialog
        this.beforeDialog = beforeDialog

        this.beforeDialog?.permissionsList = permissions
        this.deniedDialog?.permissionsList = permissions

        if (this.beforeDialog != null && shouldShowRequestPermissionsRationale(permissions)) {
            this.beforeDialog?.show()
            this.beforeDialog?.getCancelView()?.setOnClickListener {
                this.beforeDialog?.dismiss()
            }
            this.beforeDialog?.getConfirmView()?.setOnClickListener {
                this.beforeDialog?.dismiss()
                permissionsLauncher.launch(permissions)
            }
        } else {
            permissionsLauncher.launch(permissions)
        }
    }


    private fun shouldShowRequestPermissionsRationale(permissions: Array<String>): Boolean {
        permissions.forEach {
            if (shouldShowRequestPermissionRationale(it)) {
                return true
            }
        }
        return false
    }
}