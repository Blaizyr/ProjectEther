package pw.kmp.projectether

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform