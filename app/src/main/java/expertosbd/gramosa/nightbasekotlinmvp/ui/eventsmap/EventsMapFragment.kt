package expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import expertosbd.gramosa.nightbasekotlinmvp.R
import expertosbd.gramosa.nightbasekotlinmvp.di.component.DaggerFragmentComponent
import expertosbd.gramosa.nightbasekotlinmvp.di.module.FragmentModule
import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventdetail.EventDetailFragment
import expertosbd.gramosa.nightbasekotlinmvp.utils.IconFactory
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class EventsMapFragment : Fragment(), EventsMapContract.View, CardAdapter.onCardClickListener,
        CardAdapter.onCardChangeListener, OnMapReadyCallback {
    @Inject
    lateinit var presenter: EventsMapContract.Presenter

    private lateinit var rootView: View

    private var googleMap: GoogleMap? = null
    private var mapView: MapView? = null
    fun newInstance(): EventsMapFragment {
        return EventsMapFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        mapView = rootView.findViewById(R.id.map) as MapView
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()

        if (mapView != null) {
            mapView!!.onCreate(null)
            mapView!!.onResume()
            mapView!!.getMapAsync(this)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        MapsInitializer.initialize(context!!)
        googleMap = map
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        val berlinCoords = LatLng(52.520645, 13.409779)

        val cameraPosition = CameraPosition.Builder()
                .target(berlinCoords)
                .zoom(13.5f)
                .tilt(0f)
                .build()

        map?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        map?.animateCamera(CameraUpdateFactory.scrollBy(0f, 50f))

        try {
            googleMap?.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            activity, R.raw.gmap_style_json))
        } catch (e: Resources.NotFoundException) {
            Log.e("MapsActivityRaw", "Can't find style.", e)
        }

        presenter.loadData()
    }

    override fun loadDataSuccess(list: List<Event>) {
        val events = list.toMutableList()
        val adapter = CardAdapter(activity!!, events, this)

        recycler_view.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recycler_view.adapter = adapter
        recycler_view.addOnPageChangedListener(adapter)

        for (event in events) {
            val coords = LatLng(event.venue.latitude, event.venue.longitude)
            val marker = googleMap?.addMarker(MarkerOptions()
                    .position(coords)
                    .icon(IconFactory().createUnselectedIcon()))

            if (marker != null) {
                adapter.markersMap[event] = marker
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
        context?.longToast(error)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            Log.d("Progress", "loading")
        } else {
            Log.d("Progress", "finished")
        }
    }

    override fun goToMarker(marker: Marker) {
        val cameraPosition = CameraPosition.Builder()
                .target(marker.position)
                .zoom(13.5f)
                .tilt(0f)
                .build()

        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun goToEventDetails(event: Event) {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        val eventDetailFragment = EventDetailFragment()

        val bundle = Bundle()
        bundle.putSerializable("event", event)
        eventDetailFragment.arguments = bundle
        ft.replace(android.R.id.content, eventDetailFragment)
        ft.addToBackStack(null)
        ft.commit()

        Log.d(TAG, "card clicked: ${event.venue}")
    }

    private fun injectDependency() {
        val eventsMapComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        eventsMapComponent.inject(this)
    }

    companion object {
        val TAG: String = "EventsMapFragment"
    }
}