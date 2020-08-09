package alexrnov.butterflies.map

import com.google.android.gms.maps.model.LatLng
import java.util.*

internal object MapsData {

  fun getPoints(): List<ButterflyPoint> {
    val butterflies = ArrayList<ButterflyPoint>()

    butterflies.add(ButterflyPoint(LatLng(10.0, 10.0),
            "point", "snippet", "map/cacicus.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "pointnext", "snippetnext", "map/agestor.png"))

    return butterflies
  }
}