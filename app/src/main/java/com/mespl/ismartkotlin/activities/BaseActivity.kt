
package com.mespl.ismartkotlin.activities



import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    companion object {
        var mContext: Context? = null
        var perm = arrayOf(
            Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
        )

    }

    protected override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mContext = applicationContext
//        requestAppPermission()

    }



    fun GetDeviceSerial(): String {
        var serialNumber = ""
        serialNumber = if (Build.VERSION.SDK_INT >= 26) {
            Build.getSerial()
        } else {
            Build.SERIAL
        }
        return serialNumber
    }
}