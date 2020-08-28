package alexrnov.butterflies.map;

import com.google.android.gms.maps.model.LatLng;

class ButterflyPoint {

  private LatLng latLng;
  private String title;
  private String snippet;
  private String srcIcon;

  public ButterflyPoint(LatLng latLng, String title, String snippet, String srcIcon) {
    this.latLng = latLng;
    this.title = title;
    this.snippet = snippet;
    this.srcIcon = srcIcon;
  }

  public LatLng getLatLng() {
    return latLng;
  }

  public String getTitle() {
    return title;
  }

  public String getSnippet() {
    return snippet;
  }

  public String getSrcIcon() {
    return srcIcon;
  }
}
