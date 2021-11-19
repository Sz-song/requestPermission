package com.song.permission.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import com.song.permission.databinding.DialogMyDeniedBinding


class MyDeniedDialog(context: Context):DeniedDialog(context) {

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
        }

    }
    override fun getCancelView(): View {
        return binding.cancel
    }

    override fun getConfirmView(): View {
        return binding.goSetting
    }
}