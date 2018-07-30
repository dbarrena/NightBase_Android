package expertosbd.gramosa.nightbasekotlinmvp.ui.main

import expertosbd.gramosa.nightbasekotlinmvp.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun showEventsMapFragment()
        fun showEventDetailsFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onEventCardClick()
    }
}