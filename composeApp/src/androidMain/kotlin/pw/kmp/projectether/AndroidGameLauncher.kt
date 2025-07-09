package pw.kmp.projectether

import android.content.Context
import android.content.Intent
import org.godotengine.godot.GodotActivity
import pw.kmp.projectether.component.GameLauncher

class AndroidGameLauncher(
    private val context: Context,
//    private val clientPath: String,
//    private val packageName: String
) : GameLauncher {
    override fun launchGodotClient() {
        val launchIntent = Intent(context, GodotEngine::class.java)
        launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(launchIntent)
    }

    /*
        override fun launchGodotClient() {
            val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)

            if (launchIntent != null) {
                context.startActivity(launchIntent)
            } else {
                val file = File(clientPath)
                val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                val installIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                    setDataAndType(uri, "application/vnd.android.package-archive")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                }
                context.startActivity(installIntent)
            }
        }
    */
}

class GodotEngine : GodotActivity()