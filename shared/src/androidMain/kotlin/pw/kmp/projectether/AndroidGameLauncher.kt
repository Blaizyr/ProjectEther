package pw.kmp.projectether

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import pw.kmp.projectether.component.GameLauncher
import java.io.File

class AndroidGameLauncher(private val context: Context) : GameLauncher {
    override fun launchGodotClient() {
        val launchIntent = context.packageManager.getLaunchIntentForPackage("com.projectether.godotclient")

        if (launchIntent != null) {
            context.startActivity(launchIntent)
        } else {
            val file = File("/storage/emulated/0/Download/project_ether/game-android.apk")
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
            val installIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                setDataAndType(uri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(installIntent)
        }
    }
}
