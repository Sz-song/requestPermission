package com.song.permission


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.song.permission.callback.PermissionsCallback
import com.song.permission.dialog.MyDeniedDialog
import com.song.permission.dialog.PermissionDialog

class RequestPermission {

    companion object {
        private const val FRAGMENT_TAG = "RequestFragment"
    }

    private var activity: FragmentActivity? = null
    private var fragment: Fragment? = null
    private var permissions: Array<String> ?= null
    var permissionCallback: PermissionsCallback? = null
    private var deniedDialog: PermissionDialog? = null
    private var beforeDialog: PermissionDialog? = null


    constructor(activity: FragmentActivity) {
        this.activity = activity
    }

    constructor(fragment: Fragment) {
        this.fragment = fragment
    }


    fun permission(vararg permission: String) : RequestPermission{
        this.permissions= arrayOf(*permission)
        return this
    }


    fun requestCallback(permissionCallback: PermissionsCallback): RequestPermission{
        this.permissionCallback= permissionCallback
        return this
    }

    fun addDeniedDialog(deniedDialog: PermissionDialog = MyDeniedDialog(activity?: fragment!!.requireContext())): RequestPermission{
        this.deniedDialog= deniedDialog
        return this
    }

    fun addBeforeDialog(beforeDialog: PermissionDialog): RequestPermission{
        this.beforeDialog= beforeDialog
        return this
    }

    fun request() {
        if(permissions==null||permissions?.size==0){
            permissionCallback?.onResult(true, listOf(), listOf())
            return
        }

        invisibleFragment.request(permissions!!, permissionCallback ,deniedDialog,beforeDialog)
    }


    private val invisibleFragment: RequestFragment
        get() {
            val existedFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
            return if (existedFragment != null) {
                existedFragment as RequestFragment
            } else {
                val invisibleFragment = RequestFragment()
                fragmentManager.beginTransaction()
                    .add(invisibleFragment, FRAGMENT_TAG)
                    .commitNowAllowingStateLoss()
                invisibleFragment
            }
        }

    private val fragmentManager: FragmentManager
        get() {
            return fragment?.childFragmentManager ?: activity?.supportFragmentManager!!
        }
}

