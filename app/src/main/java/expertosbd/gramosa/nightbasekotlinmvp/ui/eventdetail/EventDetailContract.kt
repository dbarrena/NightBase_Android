package expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail

import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import expertosbd.gramosa.nightbasekotlinmvp.ui.base.BaseContract

class EventDetailContract {

    interface View: BaseContract.View {
        fun showDetails(event: Event)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadDetails()
    }
}