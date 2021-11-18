package com.song.permission

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class RequestPermission {
    private var activity: FragmentActivity? = null
    private var fragment: Fragment? = null

    constructor(activity: FragmentActivity) {
        this.activity = activity
    }

    constructor(fragment: Fragment) {
        this.fragment = fragment
    }
    companion object {
        /**
         * TAG of RequestFragment to find and create.
         */
        private const val FRAGMENT_TAG = "RequestFragment"
    }

    fun request(permission :String) {
        invisibleFragment.request(permission)
    }
//
//    fun request(permissions: Array<String>) {
//        val permissionsLauncher =
//            activity?.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
//            { permissionsGranted: Map<String, Boolean> ->
//                permissionsGranted.entries.forEach {
//
//                }
//            }
//        permissionsLauncher?.launch(
//            permissions
//        )
//
//    }


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

