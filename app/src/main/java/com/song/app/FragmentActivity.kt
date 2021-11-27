package com.song.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.song.app.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFragmentBinding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment= PermissionFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.frame.id, fragment)
        transaction.commit()
    }
}