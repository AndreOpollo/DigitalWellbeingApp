package com.example.digitalwellbeingapp.ui.home

import android.R.attr.text
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.service.autofill.Validators.or
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.example.digitalwellbeingapp.core.util.getAppIcon
import com.example.digitalwellbeingapp.core.util.getAppName


@Composable
fun HomeContent(
    state: HomeUiState,
    onEvent: (HomeUiEvent)->Unit
){
    val context = LocalContext.current
    val launchIntent = Intent(Intent.ACTION_MAIN,null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }
    val launcherApps = context.packageManager.queryIntentActivities(
        launchIntent,
        0
    ).map { it.activityInfo.applicationInfo}



    Box(modifier = Modifier.fillMaxSize()){
        when{
            state.isLoading ->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error!=null->{
                Toast
                    .makeText(context,"Something went wrong",
                        Toast.LENGTH_LONG).show()
            }
            else ->{
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)){
                    item{
                        Column(modifier = Modifier.fillMaxWidth()){
                            Text("Total Screen Time")
                            Text(formatMillis(state.totalScreenTimeToday))
                        }
                    }
//                itemsIndexed(launcherApps){index,it->
//                    val pm = context.packageManager
//                    val appInfo = pm.getApplicationInfo(it.packageName,0)
//                    val appName = pm.getApplicationLabel(appInfo).toString()
//                    val appIcon = pm.getApplicationIcon(appInfo)
//                    val appLogo = pm.getApplicationLogo(appInfo)
//                    val painter = remember(appIcon){
//                        appIcon.toBitmap().asImageBitmap()
//                    }
//                   Image(bitmap =  painter, contentDescription = null, modifier = Modifier.size(16.dp))
//                    Text(text = "${index+1}")
//                        Text(text = appName)
//
//
//                    Spacer(modifier = Modifier.height(8.dp))
//                }
                    itemsIndexed(state.appUsageInfo){index,it->
                        Text(text="${index+1}")
                        Image(bitmap = getAppIcon(context,it.packageName),
                            contentDescription = null, modifier = Modifier.size(32.dp))
                        Text(text= getAppName(context,it.packageName))
                        Text(text=formatMillis(it.totalTimeForeground))
                        Text(text=formatMillis(it.lastTimeUsed))
                        Text(text=formatMillis(it.lastTimeStamp))

                    }
                }
            }

            }

        }
    }
fun formatMillis(millis: Long): String {
    val seconds = (millis / 1000) % 60
    val minutes = (millis / (1000 * 60)) % 60
    val hours = millis / (1000 * 60 * 60)
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

