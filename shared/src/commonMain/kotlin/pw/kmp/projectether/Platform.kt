package pw.kmp.projectether

interface Platform {
    val name: String
    val isTouch: Boolean
}

expect fun getPlatform(): Platform