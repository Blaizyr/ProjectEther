package pw.kmp.projectether.godot

import pw.kmp.projectether.Platform

interface GodotClient {
    val platform: Platform
    val isTouch: Boolean get() = platform.isTouch
}
