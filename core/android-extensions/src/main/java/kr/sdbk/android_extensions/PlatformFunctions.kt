package kr.sdbk.android_extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun showToast(context: Context, msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg, duration).show()
}

fun showToast(context: Context, @StringRes msg: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, context.getString(msg), duration).show()
}

fun getCurrentAppVersion(context: Context): String? {
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    return packageInfo.versionName // 예: "1.2.3"
}

fun getCurrentVersionCode(context: Context): Long {
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    return packageInfo.longVersionCode
}