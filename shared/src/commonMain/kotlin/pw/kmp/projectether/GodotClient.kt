package pw.kmp.projectether

interface GodotClient {
    val platform: Platform
    val isTouch: Boolean get() = platform.isTouch
}
