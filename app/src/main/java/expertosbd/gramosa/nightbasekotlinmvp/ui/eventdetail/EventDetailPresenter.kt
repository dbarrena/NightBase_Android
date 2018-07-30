package expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail

import expertosbd.gramosa.nightbasekotlinmvp.api.ApiServiceInterface
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapContract
import io.reactivex.disposables.CompositeDisposable

class EventDetailPresenter: EventDetailContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: EventDetailContract.View

    override fun loadDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: EventDetailContract.View) {
        this.view = view
    }

}