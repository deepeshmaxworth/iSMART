package com.mespl.ismartkotlin.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.mespl.ismartkotlin.R
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
        callDialog()
    }

    fun langClick(view: View) {

    }

    fun syncClick(view: View) {

    }

    fun callDialog() {
        val settingpswrd: EditText
        val texOK: TextView
        val texExit: TextView
        val dialog = Dialog(this@LoginActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.settingpasword_dialog)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        settingpswrd = dialog.findViewById(R.id.pinView)
        texOK = dialog.findViewById(R.id.al_button_ok)
        texExit = dialog.findViewById(R.id.al_button_Exit)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        settingpswrd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.length == 4) {
                    val imm =
                        this@LoginActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                }
            }
        })
        texOK.setOnClickListener(View.OnClickListener {
            if (settingpswrd.text.toString().equals("2662", ignoreCase = true)) {
                val intent = Intent(this@LoginActivity, SettingActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
                //                    finish();
            } else {
                if (!settingpswrd.text.toString().equals("2662", ignoreCase = true)) {
                    promptErrorMessage(getString(R.string.Please_Enter_Correct_Password))
                    return@OnClickListener
                } else {
                    promptErrorMessage(getString(R.string.Please_Enter_Password))
                    return@OnClickListener
                }
            }
        })
        texExit.setOnClickListener {
            dialog.dismiss()
            dialog.hide()
        }
        dialog.show()
    }

    fun promptErrorMessage(caution: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.errordialog)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setTitle("Message")
        val texOK = dialog.findViewById<LinearLayout>(R.id.al_button_ok)
        val dialogtextChange = dialog.findViewById<TextView>(R.id.al_txt_preview)
        dialogtextChange.text = caution
        dialog.show()
        texOK.setOnClickListener { dialog.dismiss() }
    }
}