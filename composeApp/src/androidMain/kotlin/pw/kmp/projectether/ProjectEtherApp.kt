package pw.kmp.projectether

import android.app.Application
import pw.kmp.projectether.di.initKoin

class ProjectEtherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}