package alexrnov.butterflies.map

import com.google.android.gms.maps.model.LatLng

internal data class ButterflyPoint(
        val latLng: LatLng, val title: String, val snippet: String, val srcIcon: String)