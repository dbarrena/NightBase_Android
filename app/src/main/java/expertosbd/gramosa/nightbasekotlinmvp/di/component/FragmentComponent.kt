package expertosbd.gramosa.nightbasekotlinmvp.di.component

import dagger.Component
import expertosbd.gramosa.nightbasekotlinmvp.di.module.FragmentModule
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail.EventDetailFragment
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapFragment

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(eventsMapFragment: EventsMapFragment)
    fun inject(eventDetailFragment: EventDetailFragment)
}