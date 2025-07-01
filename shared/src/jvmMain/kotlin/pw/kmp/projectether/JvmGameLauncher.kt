package pw.kmp.projectether

import pw.kmp.projectether.component.GameLauncher

class JvmGameLauncher : GameLauncher {
    override fun launchGodotClient() {
        val godotBinary = "A:\\proj\\godot\\game-server.exe"
        val process = ProcessBuilder(godotBinary)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
    }
}