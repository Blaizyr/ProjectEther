package pw.kmp.projectether.service

class GameServerLauncher(private val serverPath: String) {
    fun launch() {
        val process = ProcessBuilder(serverPath, "--headless")
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        Runtime.getRuntime().addShutdownHook(Thread {
            println("Stopping Godot server...")
            process.destroy()
        })

        println("🎮 Godot server launched.")
    }
}