package expertosbd.gramosa.nightbasekotlinmvp.di.component

import dagger.Component
import expertosbd.gramosa.nightbasekotlinmvp.BaseApp
import expertosbd.gramosa.nightbasekotlinmvp.di.module.ApplicationModule

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)
}