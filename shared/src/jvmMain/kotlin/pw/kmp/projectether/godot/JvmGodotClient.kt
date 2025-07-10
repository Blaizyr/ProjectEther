package pw.kmp.projectether.godot

import pw.kmp.projectether.Platform

class JvmGodotClient(
    val jvmGodotClientProcess: Process,
    override val platform: Platform
) : GodotClient