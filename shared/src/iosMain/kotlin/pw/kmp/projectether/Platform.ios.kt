package pw.kmp.projectether

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val isTouch: Boolean = true
}

actual fun getPlatform(): Platform = IOSPlatform()