package com.song.permission.callback

interface PermissionsCallback {
    fun onResult(allGranted: Boolean, grantedList: List<String>, deniedList: List<String>)
}