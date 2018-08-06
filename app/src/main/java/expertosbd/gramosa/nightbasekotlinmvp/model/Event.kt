package expertosbd.gramosa.nightbasekotlinmvp.model

import java.io.Serializable

data class Event(
        val idEvent: Int,
        val venue: Venue,
        val name: String,
        val description: String,
        val date: String,
        val price: String,
        val residentAdvisorLink: String,
        val residentAdvisorMembers: Int,
        val minimumAge: String,
        val flyerURL: String,
        val artists:ArrayList<Artist>,
        val promoters: ArrayList<Promoter>
) : Serializable