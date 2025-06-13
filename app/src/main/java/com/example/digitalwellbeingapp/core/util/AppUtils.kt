package com.example.digitalwellbeingapp.core.util

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.core.graphics.drawable.toBitmap

fun getAllLauncherApps(context: Context): List<String?> {
    val launchIntent = Intent(Intent.ACTION_MAIN,null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }
    val launcherApps = context.packageManager.queryIntentActivities(
        launchIntent,
        0
    ).map{it.activityInfo.applicationInfo.packageName}

    return launcherApps
}

fun getAppName(context: Context,packageName:String):String{
    val pm = context.packageManager
    val appInfo = pm.getApplicationInfo(packageName,0)
    val appName = pm.getApplicationLabel(appInfo)
    return appName.toString()
}
fun getAppIcon(context: Context,packageName: String): ImageBitmap{
    val pm = context.packageManager
    val appInfo = pm.getApplicationInfo(packageName,0)
    val appIcon = pm.getApplicationIcon(appInfo)

    return    appIcon.toBitmap().asImageBitmap()
}