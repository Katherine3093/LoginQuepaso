package com.example.katherine.noviembre;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class que_pasoActivity extends AppCompatActivity {

    CheckBox pinchadoCheckbox;
    CheckBox cadenaCheckbox;
    CheckBox cambioCheckbox;
    CheckBox aireCheckbox;
    CheckBox frenosCheckbox;

    private LocationManager mLocationManager;
    private Firebase ref;
    private Location mLocation;

    private Firebase.CompletionListener completionListener = new Firebase.CompletionListener() {
        @Override
        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
            if (firebaseError != null) {

                goToMapsActivity();

            }
        }
    };

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLocation = location;
            mLocationManager.removeUpdates(mLocationListener);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    private void goToMapsActivity()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);




    }

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_paso);

        mLocationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, mLocationListener);

        pinchadoCheckbox = (CheckBox) findViewById(R.id.pinchazo);
        cadenaCheckbox = (CheckBox) findViewById(R.id.cadena);
        cambioCheckbox = (CheckBox) findViewById(R.id.cambio);
        aireCheckbox = (CheckBox) findViewById(R.id.aire);
        frenosCheckbox = (CheckBox) findViewById(R.id.frenos);
    }

    public void enviar(View view) {

        boolean pinchazo = pinchadoCheckbox.isChecked();
        boolean cadena = cadenaCheckbox.isChecked();
        boolean cambio = cambioCheckbox.isChecked();
        boolean aire = aireCheckbox.isChecked();
        boolean frenos = frenosCheckbox.isChecked();


        QuePaso quePaso = new QuePaso();
        quePaso.setPinchazo(pinchazo);
        quePaso.setCadena(cadena);
        quePaso.setCambio(cambio);
        quePaso.setAire(aire);
        quePaso.setFrenos(frenos);



        Firebase.setAndroidContext(this);
        ref = new Firebase("https://brilliant-fire-9109.firebaseio.com/quepaso");
        ref.push().setValue(quePaso, completionListener);

    }

}

