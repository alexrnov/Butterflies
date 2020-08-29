package alexrnov.butterflies.map;

import alexrnov.butterflies.R;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

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

    List<ButterflyPoint> list = MapsData.INSTANCE.getPoints();
    for (ButterflyPoint point: list) {
      map.addMarker(new MarkerOptions().position(point.getLatLng())
              .title(point.getTitle())
              .snippet(point.getSnippet()))
              .setIcon(BitmapDescriptorFactory.fromAsset(point.getSrcIcon()));
    }
  }
}
