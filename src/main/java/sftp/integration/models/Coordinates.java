package sftp.integration.models;

import java.util.Map;

public class Coordinates {
    public Double lat;
    public Double lng;

    public Coordinates() {}
    public Coordinates(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    public Coordinates(Map<String, Object> coordinateMapped) {
        this.lat = (Double) coordinateMapped.get("lat");
        this.lng = (Double) coordinateMapped.get("lng");
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    public Double getLng() {
        return lng;
    }
    public void setLng(Double lng) {
        this.lng = lng;
    }
    
}