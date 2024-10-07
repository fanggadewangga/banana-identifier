package com.fangga.scan.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun hasRequiredPermission(context: Context): Boolean {
    return Constants.CAMERAX_PERMISSION.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}