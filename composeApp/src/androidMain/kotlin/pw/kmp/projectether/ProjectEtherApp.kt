package pw.kmp.projectether

import android.app.Application
import org.koin.android.ext.koin.androidContext
import pw.kmp.projectether.di.initKoin

class ProjectEtherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appDeclaration = { androidContext(this@ProjectEtherApp) },
        )
    }
}