package expertosbd.gramosa.nightbasekotlinmvp.di.component

import dagger.Component
import expertosbd.gramosa.nightbasekotlinmvp.ui.main.MainActivity
import expertosbd.gramosa.nightbasekotlinmvp.di.module.ActivityModule

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}

