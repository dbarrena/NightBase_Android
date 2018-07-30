package expertosbd.gramosa.nightbasekotlinmvp.di.module

import dagger.Module
import dagger.Provides
import expertosbd.gramosa.nightbasekotlinmvp.api.ApiServiceInterface
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail.EventDetailContract
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail.EventDetailPresenter
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapContract
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapFragment
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapPresenter

@Module
class FragmentModule {

    @Provides
    fun provideEventsMapPresenter(): EventsMapContract.Presenter {
        return EventsMapPresenter()
    }

    @Provides
    fun provideEventDetailsPresenter(): EventDetailContract.Presenter {
        return EventDetailPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}