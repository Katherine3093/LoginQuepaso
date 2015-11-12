package com.example.katherine.noviembre;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    private LocationManager locationmanager;
    private LocationManager mLocationManager;
    private PolylineOptions polylineOptions;
    private List<MarkerOptions> markerList;
    private Firebase firebase;
    private LocationListener locationCallback = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            LatLng latlng
                    = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MarkerOptions markerOptions
                    = new MarkerOptions().position(latlng);
            markerList.add(markerOptions);
            polylineOptions.add(latlng);
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.v("LOCATION", "Status cambio");
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.locationmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markerList = new ArrayList<>();
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.CYAN);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng Bogota = new LatLng(4.703791, 74.032728);
        mMap.addMarker(new MarkerOptions().position(Bogota).title("bogota"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Bogota));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                markerList.add(markerOptions);
                polylineOptions.add(latLng);
                mMap.addMarker(markerOptions);
                mMap.addPolyline(polylineOptions);
            }
        });
    }

    public void ayuda(View view) {
    }
}


