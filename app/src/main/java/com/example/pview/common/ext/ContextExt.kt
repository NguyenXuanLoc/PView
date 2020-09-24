package com.example.pview.common.ext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telecom.TelecomManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.json.JSONObject
import timber.log.Timber


fun Context.networkIsConnected(): Boolean {
    try {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return conMgr?.activeNetworkInfo?.isConnected ?: false
    } catch (e: Exception) {
        Timber.e("$e")
    }

    return false
}


fun Context.requestDrawOverlayPermission(requestCode: Int) {
    val intent = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:$packageName")
    )
    when (this) {
        is AppCompatActivity -> {
            startActivityForResult(intent, requestCode)
        }
        is Fragment -> {
            startActivityForResult(intent, requestCode)
        }
    }
}

fun Context.openAppSettingsPage() {
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        data = Uri.parse("package:$packageName")
    }.run {
        startActivity(this)
    }
}


fun Context.convertMapToJSONObject(map: Map<String, Any?>): JSONObject {
    val jsonObject = JSONObject()
    for (key in map.keys) {
        val value = if (map[key] is Map<*, *>) {
            convertMapToJSONObject(map[key] as Map<String, Any?>).toString()
        } else {
            map[key]
        }
        jsonObject.put(key, value)
    }
    return jsonObject
}