package alexrnov.butterflies.map

import com.google.android.gms.maps.model.LatLng
import java.util.*

internal object MapsData {

  fun getPoints(): List<ButterflyPoint> {
    val butterflies = ArrayList<ButterflyPoint>()

    butterflies.add(ButterflyPoint(LatLng(30.0, 71.0),
            "agestor", "Pakistan", "map/agestor.png"))

    butterflies.add(ButterflyPoint(LatLng(42.0, 22.0),
            "alexanor", "Balkans", "map/alexanor.png"))

    butterflies.add(ButterflyPoint(LatLng(1.0, 13.0),
            "antimachus", "west Africa", "map/antimachus.png"))

    butterflies.add(ButterflyPoint(LatLng(23.0, 80.0),
            "arcturus", "Indian", "map/arcturus.png"))

    butterflies.add(ButterflyPoint(LatLng(-26.0, -62.0),
            "astyalus", "Argentina", "map/astyalus.png"))

    butterflies.add(ButterflyPoint(LatLng(6.0, -64.0),
            "bachus", "Venezuela", "map/bachus.png"))

    butterflies.add(ButterflyPoint(LatLng(13.0, 121.0),
            "benguetanus", "Philippines", "map/benguetanus.png"))

    butterflies.add(ButterflyPoint(LatLng(48.5, -56.0),
            "brevicauda", "Newfoundland", "map/brevicauda.png"))

    butterflies.add(ButterflyPoint(LatLng(-9.6, 160.0),
            "bridgei", "Solomons Islands", "map/bridgei.png"))

    butterflies.add(ButterflyPoint(LatLng(-11.0, -73.0),
            "cacicus", "Peru", "map/cacicus.png"))

    butterflies.add(ButterflyPoint(LatLng(21.0, -77.0),
            "caiguanabus", "Cuba", "map/caiguanabus.png"))

    butterflies.add(ButterflyPoint(LatLng(1.0, -60.0),
            "chiansiades", "South America", "map/chiansiades.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "chikae", "place 13", "map/chikae.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "chrapkowskii", "place 14", "map/chrapkowskii.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "cynorta", "place 15", "map/cynorta.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "deiphobus", "place 16", "map/deiphobus.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "delalandei", "place 17", "map/delalandei.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "echerioides", "place 18", "map/echerioides.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "epiphorbas", "place 19", "map/epiphorbas.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "euterpinus", "place 20", "map/euterpinus.png"))


    return butterflies
  }
}