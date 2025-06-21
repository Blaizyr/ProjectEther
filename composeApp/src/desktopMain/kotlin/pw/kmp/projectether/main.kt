package pw.kmp.projectether

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import pw.kmp.projectether.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ProjectEther",
    ) {
        App()
    }
}