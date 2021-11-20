package com.song.permission.dialog

import android.app.Dialog
import android.content.Context
import android.view.View

abstract class DeniedDialog(context: Context):Dialog(context){

    private val deniedList: MutableList<String> = mutableListOf()

    abstract fun getCancelView():View
    abstract fun getConfirmView():View

    fun getDeniedList(deniedList: MutableList<String>){
        this.deniedList.clear()
        deniedList.addAll(deniedList)
    }
}