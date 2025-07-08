package pw.kmp.projectether

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isTouch: Boolean = true
}

actual fun getPlatform(): Platform = AndroidPlatform()