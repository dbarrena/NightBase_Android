package expertosbd.gramosa.nightbasekotlinmvp.ui.eventsmap

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.maps.model.Marker
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager
import expertosbd.gramosa.nightbasekotlinmvp.R
import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import expertosbd.gramosa.nightbasekotlinmvp.utils.IconFactory

class CardAdapter(private val context: Context, private val events: MutableList<Event>,
                  fragment: Fragment) : RecyclerView.Adapter<CardAdapter.CardViewHolder>(),
        RecyclerViewPager.OnPageChangedListener {

    private val cardClickListener: CardAdapter.onCardClickListener
    private val cardChangeListener: CardAdapter.onCardChangeListener

    val markersMap = hashMapOf<Event, Marker>()

    init {
        this.cardClickListener = fragment as CardAdapter.onCardClickListener
        this.cardChangeListener = fragment as CardAdapter.onCardChangeListener
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun OnPageChanged(fromPosition: Int, toPosition: Int) {
        val unselectedEvent = events[fromPosition]
        val unselectedMarker = markersMap[unselectedEvent]

        val selectedEvent = events[toPosition]
        val selectedMarker = markersMap[selectedEvent]

        if (selectedMarker != null && unselectedMarker != null) {
            cardChangeListener.goToMarker(selectedMarker)
            unselectedMarker.setIcon(IconFactory().createUnselectedIcon())
            selectedMarker.setIcon(IconFactory().createSelectedIcon(context))
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)

        holder.cardView.setOnClickListener {
            cardClickListener.goToEventDetails(event)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.event_card, parent, false)
        return CardAdapter.CardViewHolder(itemView)
    }


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTitle = itemView.findViewById<TextView>(R.id.event_title)
        val eventVenue = itemView.findViewById<TextView>(R.id.event_venue)
        val cardView = itemView.findViewById<CardView>(R.id.event_card)
        val price = itemView.findViewById<TextView>(R.id.price)

        fun bind(item: Event) {
            eventTitle?.text = item.name
            eventVenue?.text = "at " + item.venue.name
            price?.text = item.price
        }
    }

    interface onCardClickListener {
        fun goToEventDetails(event: Event)
    }

    interface onCardChangeListener {
        fun goToMarker(marker: Marker)
    }

}

