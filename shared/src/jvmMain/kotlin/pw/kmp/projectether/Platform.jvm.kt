package pw.kmp.projectether

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val isTouch: Boolean = false
}

actual fun getPlatform(): Platform = JVMPlatform()