package com.song.permission.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.song.permission.R
import com.song.permission.databinding.DialogMyDeniedBinding
import com.song.permission.permissionMapOnR


class MyDeniedDialog(context: Context):PermissionDialog(context) {

    private lateinit var binding: DialogMyDeniedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogMyDeniedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.let {
            val param = it.attributes
            val width = (context.resources.displayMetrics.widthPixels * 0.8).toInt()
            val height = param.height
            it.setLayout(width, height)
            it.setBackgroundDrawable(
                ResourcesCompat.getDrawable(context.resources,
                    R.color.transparent,null))
        }
        if(deniedList.isNotEmpty()&&getPermissionStr().isNotEmpty()){
            binding.permissions.text=getPermissionStr()
        }
    }
    override fun getCancelView(): View {
        return binding.cancel
    }

    override fun getConfirmView(): View {
        return binding.goSetting
    }

    private fun getPermissionStr() :String{
        val stringBuilder=StringBuffer()
        for (permission in deniedList) {
            val permissionGroup = permissionMapOnR[permission]
            if (permissionGroup != null ) {
                val text = context.packageManager.getPermissionGroupInfo(permissionGroup, 0).loadLabel(context.packageManager)
                if(!stringBuilder.contains(text)){
                    if(stringBuilder.isNotEmpty()){
                        stringBuilder.append("\n")
                        stringBuilder.append("- ")
                        stringBuilder.append(text)
                    }else{
                        stringBuilder.append("- ")
                        stringBuilder.append(text)
                    }
                }
            }
        }
        return stringBuilder.toString()
    }
}