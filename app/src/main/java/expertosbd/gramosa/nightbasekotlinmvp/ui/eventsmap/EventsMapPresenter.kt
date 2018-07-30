package expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap

import expertosbd.gramosa.nightbasekotlinmvp.api.ApiServiceInterface
import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EventsMapPresenter: EventsMapContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: EventsMapContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: EventsMapContract.View) {
        this.view = view
    }

    override fun loadData() {
        var subscribe = api.getEvents().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list: List<Event>? ->
                    view.showProgress(false)
                    view.loadDataSuccess(list!!.take(20))
                }, {error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }
}