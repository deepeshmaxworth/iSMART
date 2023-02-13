package com.mespl.ismartkotlin.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mespl.ismartkotlin.databinding.ActivitySettingBinding
import com.mespl.ismartkotlin.utils.getDeviceSerial

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var mCtx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
    }

    private fun initView(binding: ActivitySettingBinding) {
        binding.deviceIdTextv.text = "Serial Number : ${getDeviceSerial()}"

    }
}