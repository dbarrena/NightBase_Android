package expertosbd.gramosa.nightbasekotlinmvp.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import expertosbd.gramosa.nightbasekotlinmvp.R
import expertosbd.gramosa.nightbasekotlinmvp.di.component.DaggerActivityComponent
import expertosbd.gramosa.nightbasekotlinmvp.di.module.ActivityModule
import expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap.EventsMapFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
    }

    override fun showEventDetailsFragment() {

    }

    override fun showEventsMapFragment() {
        if (supportFragmentManager.findFragmentByTag(EventsMapFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content, EventsMapFragment().newInstance(), EventsMapFragment.TAG)
                    .commit()
        } else {
            // Maybe an animation like shake hello text
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }
}
