package expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap

import android.service.autofill.FillEventHistory
import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import expertosbd.gramosa.nightbasekotlinmvp.ui.base.BaseContract

class EventsMapContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Event>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}