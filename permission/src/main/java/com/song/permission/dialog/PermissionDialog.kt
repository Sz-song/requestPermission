package com.song.permission.dialog

import android.app.Dialog
import android.content.Context
import android.view.View

abstract class PermissionDialog(context: Context):Dialog(context){
    var permissionsList:Array<String> = arrayOf()
    var deniedList: MutableList<String> = mutableListOf()

    abstract fun getCancelView():View
    abstract fun getConfirmView():View

}