package pw.kmp.projectether

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import pw.kmp.projectether.di.initKoin
import pw.kmp.projectether.di.jvmModule

fun main() = application {
    initKoin(platformModule = jvmModule)
    Window(
        onCloseRequest = ::exitApplication,
        title = "ProjectEther",
    ) {
        App()
    }
}