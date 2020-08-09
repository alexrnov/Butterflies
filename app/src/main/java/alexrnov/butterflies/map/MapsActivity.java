package alexrnov.butterflies.map;

import alexrnov.butterflies.R;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    if (mapFragment != null) {
      mapFragment.getMapAsync(this);
    }
  }

  /**
   * Manipulates the map once available. This callback is triggered when the map is ready to be used.
   * If Google Play services is not installed on the device, the user will be prompted to install
   * it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */
  @Override
  public void onMapReady(GoogleMap googleMap) {
    map = googleMap;
    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

    LatLng sydney = new LatLng(40, 10);
    map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    map.addMarker(new MarkerOptions().position(new LatLng(68, 114)));
    map.addMarker(new MarkerOptions().position(new LatLng(-40,-40)));

    map.addMarker(new MarkerOptions().position(new LatLng(0, -30))).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
    map.addMarker(new MarkerOptions().position(new LatLng(0, -10))).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
    //map.addMarker(new MarkerOptions().position(new LatLng(10, 10)).title("Marker in Sydney").snippet("Population: 4,137,400")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_pic1));

    List<ButterflyPoint> list = MapsData.f();
    for (ButterflyPoint point: list) {
      map.addMarker(new MarkerOptions().position(point.getLatLng())
              .title(point.getTitle())
              .snippet(point.getSnippet()))
              .setIcon(BitmapDescriptorFactory.fromAsset(point.getSrcIcon()));
    }
    map.addMarker(new MarkerOptions().position(new LatLng(10, 10)).title("Marker in Sydney").snippet("Population: 4,137,400")).setIcon(BitmapDescriptorFactory.fromAsset("map/cacicus.png"));

    map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(10,0)));
  }
}
