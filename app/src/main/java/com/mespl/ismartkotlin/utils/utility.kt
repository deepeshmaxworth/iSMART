package com.mespl.ismartkotlin.utils


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import android.view.View

import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar

import com.mespl.ismartkotlin.BuildConfig
import com.mespl.ismartkotlin.R
import java.io.File
import java.io.FileOutputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


fun isConnectedToInernet(mCtx: Context): Boolean {
    val manager: ConnectivityManager =
        (mCtx as AppCompatActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (manager != null) {
        val infos: Array<out NetworkInfo> = manager.allNetworkInfo
        if (infos != null) {
            for (s in infos.indices) {
                if (infos[s].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
    }
    return false
}

fun promptErrorMessage(caution: String?, mCtx: Context) {
    val dialog = Dialog(mCtx)
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

fun promptMessage(caution: String?, mCtx: Context) {
    val dialog = Dialog(mCtx)
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

fun showSnackbar(parentView: View, msg: String, mCtx: Context) {
    val snack = Snackbar.make(parentView, msg, Snackbar.LENGTH_LONG)
        .setAction("OK") {

        }.setActionTextColor(mCtx.resources.getColor(android.R.color.holo_blue_dark))
    snack.show()
}

fun getDeviceSerial(): String? {
    var serialNumber: String? = ""
    serialNumber = if (Build.VERSION.SDK_INT >= 26) {
        Build.getSerial()
    } else {
        Build.SERIAL
    }
    return serialNumber
}

fun getcaptcha(count: Int): String? {
    val alphaNumeric = "abcdefghijklmnopqrstuvwxyz"
    var count = count
    val builder = StringBuilder()
    while (count-- != 0) {
        val character: Int = (Math.random() * alphaNumeric.length).toInt()
        builder.append(alphaNumeric[character])
    }
    return builder.toString()
}

fun toolbarWithBackAndTitle(toolbar: Toolbar?, context: Context, title: String?) {
    context.let {
        (context as AppCompatActivity).setSupportActionBar(toolbar)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        context.supportActionBar?.title = title
    }
}

fun toolbarWithOutBackAndTitle(toolbar: Toolbar?, context: Context, title: String?) {
    context.let {
        (context as AppCompatActivity).setSupportActionBar(toolbar)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        context.supportActionBar?.title = title
    }
}


fun shareFile(context: Context, subject: String, fileName: String) {
    MediaScannerConnection.scanFile(
        context, arrayOf(
            Environment.getExternalStorageDirectory()
                .toString() + "/" + context.resources.getString(R.string.app_name) + "/" + fileName
        ), null
    ) { newpath, newuri ->
        Log.i("ExternalStorage", "Scanned $newpath")
        Log.i("ExternalStorage", "newuri $newuri")

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "application/*"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, subject)
        intent.putExtra(Intent.EXTRA_STREAM, newuri)
        context.startActivity(Intent.createChooser(intent, "Share To"))
    }
}

fun shareImage(context: Context, bitmap: Bitmap, eventName: String) {

    val uri = getImageUri(bitmap, context)
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "image/*"
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, eventName)
    shareIntent.putExtra(Intent.EXTRA_TEXT, eventName)

    /* val resInfoList: List<ResolveInfo> =
         context.packageManager.queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY)

     for (resolveInfo in resInfoList) {
         val packageName = resolveInfo.activityInfo.packageName
         context.grantUriPermission(
             packageName,
             uri,
             Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
         )
     }*/
    context.startActivity(Intent.createChooser(shareIntent, "Share To"))
}

fun getImageUri(bitmap: Bitmap, context: Context): Uri {
    val imgFolder = File(context.cacheDir, "images")
    var uri: Uri? = null
    try {
        var isDirectoryCreated = imgFolder.exists()
        if (!isDirectoryCreated) {
            isDirectoryCreated = imgFolder.mkdir()
        }

        if (isDirectoryCreated) {
            val filePath = File(imgFolder, System.currentTimeMillis().toString().plus("$.jpg"))
            val outputStream = FileOutputStream(filePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
            uri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider",
                filePath
            )
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return uri!!
}

/* public static void checkFolder(Context ctx, String fileUrl) {
       // fileN = fileName;
        Log.d("path fileUrl ", ""+fileUrl);
        String [] filew = fileUrl.split("/");
        fileN = filew[filew.length-1];

        Log.d("path fileN ", ""+fileN);
        String path = Environment.getExternalStorageDirectory() + "/" + ctx.getResources().getString(R.string.app_name) + "/";
        File dir = new File(path);
        Log.d("path ", ""+path);
        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            // do something
            Log.d("Folder", "Already Created");

            File pdfFile = new File(path+"/"+fileN);
            if(!pdfFile.exists()){
                if(Permission.Companion.storagePermission(ctx)){
                    newDownload(fileUrl, ctx);
                }
            }else {
                showFile(fileN, ctx);
            }
        }

    }*/

fun checkforNull(value: String): String {

    value.let {
        return if (value.equals(null, true)) {
            ""
        } else {
            value
        }
    }

}
/* public static String checkforNull(String value) {
        if (value != null) {
            if (value.equalsIgnoreCase("null")) {
                return "";
            } else {
                return value;
            }
        } else {
            return "";
        }
    }*/

fun btnEnable(view: View) {
    view.alpha = 1.0f
    view.isEnabled = true
}

fun btnDisable(view: View) {
    view.alpha = 0.5f
    view.isEnabled = false
}


fun getfileExtension(fileName: String): String {
    return fileName.substring(fileName.lastIndexOf("."))
}

fun generateRandomNo(): Int {
    return 1234 + Random.nextInt(8756)
}

fun toastShow(msg: String, mCtx: Context) {
    Toast.makeText(mCtx, msg, Toast.LENGTH_LONG).show()
}

fun alertError(msg: String, mCtx: Context) {
    var builder: AlertDialog.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        AlertDialog.Builder(mCtx, androidx.appcompat.R.style.AlertDialog_AppCompat_Light)
    } else {
        AlertDialog.Builder(mCtx)
    }
    builder.setCancelable(false)
    builder.setMessage(msg)
    builder.setIcon(android.R.drawable.ic_dialog_alert)
    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
        dialog.dismiss()
    })
    builder.show()

}

fun finishActivity(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        ActivityCompat.finishAfterTransition(context as Activity)
    } else {
        (context as Activity).finish()
    }
}


fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}

/*Date format*/
fun dateFormat1(dateString: String): String? {
    //2021-02-11T18:01:46.71
    val formatter = SimpleDateFormat("yyyy-MM-dd,  hh:mm a")
    // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy,  hh:mm a");
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = dateString.toLong()
    return formatter.format(calendar.getTime())
}

//14-8-2020
fun dateFormat_date(dateString: String?): String? {
    val fromUser = SimpleDateFormat("dd-MM-yyyy")
    val myFormat = SimpleDateFormat("yyyy-MM-dd")
    var reformattedStr: String? = ""
    try {
        reformattedStr = myFormat.format(fromUser.parse(dateString))
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return reformattedStr
}

fun dateFormat_date2(dateString: String?): String? {
    //2021-08-09T15:09:33.52
    //yyyy-MM-dd'T'HH:mm:ss.SSSZ
    //dateString = "2021-02-19T11:05:19.32";
    //SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    var reformattedStr = ""
    try {
        val fromUser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val myFormat = SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        //val myFormat = SimpleDateFormat("dd-MM-yyyy")
        reformattedStr = myFormat.format(fromUser.parse(dateString))
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    Log.e("dateFormat_date2 ", reformattedStr)
    return reformattedStr
}

fun dateFormat_date3(dateString: String?): String? {
    //2021-02-19T11:05:19.325-0700
    //yyyy-MM-dd'T'HH:mm:ss.SSSZ
    //dateString = "2021-02-19T11:05:19.32";
    //SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    var reformattedStr = ""
    try {
        val fromUser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val myFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a")
        //SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy" + "");
        reformattedStr = myFormat.format(fromUser.parse(dateString))
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    Log.e("dateFormat_date2 ", reformattedStr)
    return reformattedStr
}

fun dateFormat(dateString: String): String {
    try {
        val fromUser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val myFormat = SimpleDateFormat("dd-MM-yyyy HH:mm aaa")
        return myFormat.format(fromUser.parse(dateString))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

/*fun setUrlImg(context: Context, imageView: ImageView, imgUrl: String) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context.applicationContext)
        .asBitmap()
        .load(imgUrl)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_menu_report_image)
        // .fitCenter()
        .into(imageView)
}*/






