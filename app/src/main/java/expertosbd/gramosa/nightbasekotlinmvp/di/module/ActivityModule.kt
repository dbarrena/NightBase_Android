package expertosbd.gramosa.nightbasekotlinmvp.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import expertosbd.gramosa.nightbasekotlinmvp.ui.main.MainContract
import expertosbd.gramosa.nightbasekotlinmvp.ui.main.MainPresenter

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}