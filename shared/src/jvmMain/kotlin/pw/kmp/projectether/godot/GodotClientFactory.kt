package pw.kmp.projectether.godot

import pw.kmp.projectether.getPlatform

actual fun launchGodotClient(): GodotClient {
    val godotBinary = "A:\\proj\\godot\\game-client-win.exe"
    val process = ProcessBuilder(godotBinary)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
    return JvmGodotClient(process, getPlatform())
}
