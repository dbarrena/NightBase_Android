package expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import expertosbd.gramosa.nightbasekotlinmvp.R
import expertosbd.gramosa.nightbasekotlinmvp.di.component.DaggerFragmentComponent
import expertosbd.gramosa.nightbasekotlinmvp.di.module.FragmentModule
import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import javax.inject.Inject

class EventDetailFragment: Fragment(), EventDetailContract.View {
    @Inject
    lateinit var presenter: EventDetailContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): EventDetailFragment {
        return EventDetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.event_detail, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun showDetails(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val eventDetailComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        eventDetailComponent.inject(this)
    }

    private fun initView() {
        presenter.loadDetails()
    }

    companion object {
        val TAG: String = "EventDetailFragment"
    }

}