package expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail

import expertosbd.gramosa.nightbasekotlinmvp.api.ApiServiceInterface
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapContract
import io.reactivex.disposables.CompositeDisposable

class EventDetailPresenter: EventDetailContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: EventDetailContract.View

    override fun loadDetails() {
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: EventDetailContract.View) {
        this.view = view
    }

}