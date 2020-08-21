package alexrnov.butterflies.map

import com.google.android.gms.maps.model.LatLng
import java.util.*

internal object MapsData {

  fun getPoints(): List<ButterflyPoint> {
    val butterflies = ArrayList<ButterflyPoint>()

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "agestor", "place 1", "map/agestor.png"))

    butterflies.add(ButterflyPoint(LatLng(10.0, 10.0),
            "alexanor", "southern swallowtail", "map/alexanor.png"))

    butterflies.add(ButterflyPoint(LatLng(100.0, 00.0),
            "antimachus", "place 3", "map/antimachus.png"))

    butterflies.add(ButterflyPoint(LatLng(150.0, 40.0),
            "arcturus", "place 4", "map/arcturus.png"))

    butterflies.add(ButterflyPoint(LatLng(30.0, 20.0),
            "astyalus", "place 5", "map/astyalus.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 30.0),
            "bachus", "place 6", "map/bachus.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, -20.0),
            "benguetanus", "place 7", "map/benguetanus.png"))

    butterflies.add(ButterflyPoint(LatLng(-20.0, 20.0),
            "brevicauda", "place 8", "map/brevicauda.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "bridgei", "place 9", "map/bridgei.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "cacicus", "place 10", "map/cacicus.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "caiguanabus", "place 11", "map/caiguanabus.png"))

    butterflies.add(ButterflyPoint(LatLng(20.0, 20.0),
            "chiansiades", "place 12", "map/chiansiades.png"))

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