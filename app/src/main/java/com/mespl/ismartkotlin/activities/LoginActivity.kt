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
import android.widget.TextView
import com.mespl.ismartkotlin.R
import com.mespl.ismartkotlin.databinding.ActivityLoginBinding
import com.mespl.ismartkotlin.pta.DashboardActivity
import com.mespl.ismartkotlin.utils.Konstants.CaptchaStatus
import com.mespl.ismartkotlin.utils.getcaptcha
import com.mespl.ismartkotlin.utils.promptErrorMessage
import com.mespl.ismartkotlin.utils.showSnackbar


class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mCtx: Context
    private lateinit var btnlogin: TextView
    private lateinit var tvCaptcha: TextView
    private lateinit var etPassword: EditText
    private lateinit var etUserid: EditText
    private lateinit var etCaptcha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
    }

    private fun initView(binding: ActivityLoginBinding) {
        this.also { mCtx = it }
        btnlogin = binding.btnlogin
        tvCaptcha = binding.tvCaptcha
        etPassword = binding.etPassword
        etUserid = binding.etUserid
        etCaptcha = binding.etCaptcha
        tvCaptcha.text = getcaptcha(4)
    }


    fun loginClick(view: View) {
        if (etUserid.text.toString() != "" && etPassword.text.toString() != ""
        ) {
            if (etCaptcha.text.toString() == tvCaptcha.text.toString() || !CaptchaStatus
            ) {
//                GetLogin()
                showSnackbar(view, "Login Successful", this)
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                promptErrorMessage(getString(R.string.Enter_Valid_Captcha), this)
                tvCaptcha.text = getcaptcha(4)
            }
        } else {
            promptErrorMessage(getString(R.string.Please_Check_Userid_and_Password), this)
            tvCaptcha.text = getcaptcha(4)
            return
        }
    }

    fun settingClick(view: View) {
        callDialog()
    }

    fun langClick(view: View) {

    }

    fun syncClick(view: View) {

    }


    fun refreshClick(view: View) {
        tvCaptcha.text = getcaptcha(4)
        etCaptcha.setText("")
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
                    promptErrorMessage(getString(R.string.Please_Enter_Correct_Password), this)
                    return@OnClickListener
                } else {
                    promptErrorMessage(getString(R.string.Please_Enter_Password), this)
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

    override fun onResume() {
        super.onResume()
        tvCaptcha.text = getcaptcha(4)
    }


}