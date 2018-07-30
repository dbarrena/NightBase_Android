package expertosbd.gramosa.nightbasekotlinmvp.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import expertosbd.gramosa.nightbasekotlinmvp.BaseApp

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}