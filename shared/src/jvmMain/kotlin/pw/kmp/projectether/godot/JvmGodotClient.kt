package pw.kmp.projectether.godot

import pw.kmp.projectether.Platform

class JvmGodotClient(
    jvmGodotClientProcess: Process,
    override val platform: Platform
) : GodotClient