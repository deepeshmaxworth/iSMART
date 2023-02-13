package com.mespl.ismartkotlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.mespl.ismartkotlin.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mCtx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
    }

    private fun initView(binding: ActivityLoginBinding) {
        this.also { mCtx = it }
        val btnlogin: LinearLayout = binding.btnlogin
    }


    fun loginClick(view: View) {

    }

    fun settingClick(view: View) {
        startActivity(Intent(mCtx, SettingActivity::class.java))
    }

    fun langClick(view: View) {

    }

    fun syncClick(view: View) {

    }
}