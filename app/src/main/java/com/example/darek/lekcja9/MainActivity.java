package com.example.darek.lekcja9;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView dostawca;
    TextView dlugosc;
    TextView szerokosc;

    Criteria cr;
    Location loc;
    String mojDostawca;

    LocationManager mylm;

    //boolean isGPSEnabled = false;
    //boolean isNetworkEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dostawca = (TextView) findViewById(R.id.textView1);
        dlugosc = (TextView) findViewById(R.id.textView2);
        szerokosc = (TextView) findViewById(R.id.textView3);

        cr = new Criteria();
        mylm = (LocationManager) getSystemService(LOCATION_SERVICE);
        mojDostawca = mylm.getBestProvider(cr, false);

        //isGPSEnabled = mylm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //isNetworkEnabled = mylm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        loc = mylm.getLastKnownLocation(mojDostawca);

        //onLocationChanged(loc);
        //showLocation();
        if (loc != null) {
            //showLocation();
            onLocationChanged(loc);
        } else {
            dlugosc.setText("Lokalizacja nie jest dostępna");
            szerokosc.setText("Lokalizacja nie jest dostępna");
        }

    }

    public void showLocation() {
        dostawca.setText("Dostawca: " + mojDostawca);
        dlugosc.setText("Dlugosc geograficzna: " + loc.getLongitude());
        szerokosc.setText("Szerokosc geograficzna: " + loc.getLatitude());
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mylm.requestLocationUpdates(mojDostawca, 400, 1, (LocationListener) this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mylm.removeUpdates((LocationListener) this);
    }
*/
    protected void onLocationChanged(Location location) {
        mojDostawca = mylm.getBestProvider(cr, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        loc = mylm.getLastKnownLocation(mojDostawca);
        showLocation();
    }

    public void onProviderDisabled(String provider) {
        onLocationChanged(null);
    }

/*
    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
*/
}
