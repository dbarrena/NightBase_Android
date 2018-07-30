package expertosbd.gramosa.nightbasekotlinmvp.model

data class Venue(
        val idVenue: Int,
        val name: String,
        val country: String,
        val address: String,
        val logoLink: String,
        val latitude: Double,
        val longitude: Double
)
