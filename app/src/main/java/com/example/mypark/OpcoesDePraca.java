package com.example.mypark;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.Math;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class OpcoesDePraca extends AppCompatActivity {

    private Location location;
    protected static final int TIMER_RUNTIME = 50000;
    private Double latitudeUsuario;
    private Double longitudeUsuario;
    private Double result;

    private GoogleApiClient mGoogleApiClient;
    FusedLocationProviderClient mFusedLocationClient;

    TextView textView2;
    FirebaseFirestore fireStore;

    final ArrayList<DeviceLocation> LatLong = new ArrayList<DeviceLocation>();

    int PERMISSION_ID = 44;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pracas);
        setTitle("MySquare");
        fireStore = FirebaseFirestore.getInstance();
        final ListView lista = (ListView) findViewById(R.id.listPracas);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


        Intent i = getIntent();
        Boolean Skate = i.getBooleanExtra("skatee", false);
        Boolean areaCri = i.getBooleanExtra("AreaCrianca", false);
        Boolean corrida = i.getBooleanExtra("Corrida", false);
        Boolean areaDog = i.getBooleanExtra("areaDog", false);

        //ConverteLatitude(latitudeUsuario, longitudeUsuario, "-29.926859", "-51.039984");

        if (Skate) {
            final ArrayList<Praca> pracas = new ArrayList<Praca>();
            fireStore.collection("Parks").document("PracasGravatai").collection("Skate").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String uid = (document.getId());
                            String nome = (document.getString("Nome"));
                            String instalacoes = (document.getString("Facilidades"));
                            String endereco = (document.getString("Endereco"));
                            ArrayList imagem = (ArrayList) document.get("Imagens");
                            Number latitudePraca = (Number) document.get("Latitude");
                            Number longitudePraca = (Number) document.get("Longitude");

                            Praca p = new Praca(uid, nome, instalacoes, endereco, imagem, latitudePraca, longitudePraca);
                            pracas.add(p);
                            ArrayAdapter adapter = new PracaAdapter(OpcoesDePraca.this, pracas);
                            lista.setAdapter(adapter);
                        }
                    }
                }
            });
        }
        if (areaCri) {
            final ArrayList<Praca> pracas = new ArrayList<Praca>();
            fireStore.collection("Parks").document("PracasGravatai").collection("AreaCrianca").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String uid = (document.getId());
                            String nome = (document.getString("Nome"));
                            String instalacoes = (document.getString("Instalacoes"));
                            String endereco = (document.getString("Endereco"));
                            ArrayList imagem = (ArrayList) document.get("Imagens");
                            Number latitudePraca = (Number) document.get("Latitude");
                            Number longitudePraca = (Number) document.get("Longitude");


                            Praca p = new Praca(uid, nome, instalacoes, endereco, imagem, latitudePraca, longitudePraca);
                            pracas.add(p);
                            ArrayAdapter adapter = new PracaAdapter(OpcoesDePraca.this, pracas);
                            lista.setAdapter(adapter);
                        }
                    }
                }
            });
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                Praca itemValue = (Praca) lista.getItemAtPosition(position);
                DeviceLocation item = (DeviceLocation) LatLong.get(position);

                String uid = itemValue.getUid();
                String nome = itemValue.getNome();
                String Instalacoes = itemValue.getFacilidades();
                String endereco = itemValue.getEndereco();
                ArrayList imagem = itemValue.getImagem();
                Number latitude = itemValue.getLatitude();
                Number longitude = itemValue.getLongitude();

                Number latitudeDevice = item.getLatitudeDevice();
                Number longitudeDevice = item.getLongitudeDevide();


                Intent i = new Intent(OpcoesDePraca.this, DetalhesActivity.class);

                i.putExtra("uid", uid);
                i.putExtra("nome", nome);
                i.putExtra("Instalacoes", Instalacoes);
                i.putExtra("endereco", endereco);
                i.putExtra("imagem", imagem);
                i.putExtra("latitude", latitude);
                i.putExtra("longitude", longitude);


                i.putExtra("latitudeDevice", latitudeDevice);
                i.putExtra("longitudeDevice", longitudeDevice);


                startActivity(i);


            }
        });


    }
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    Number latitude = location.getLatitude();
                                    Number longitude = location.getLongitude();
                                    DeviceLocation d = new DeviceLocation(latitude,longitude);
                                    LatLong.add(d);
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult (LocationResult locationResult) {
            Location  mLastLocation = locationResult.getLastLocation ();
            Number latitude = location.getLatitude();
            Number longitude = location.getLongitude();
            DeviceLocation d = new DeviceLocation(latitude,longitude);
            LatLong.add(d);
        }
    };

    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }


    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }


   /* public void ConverteLatitude(Double latitude1, Double logitude1, String latitude2, String logitude2) {

        final int R = 6371; // Radious of the earth
        Double lat1 = latitude1;
        Double lon1 = logitude1;
        Double lat2 = Double.parseDouble(latitude2);
        Double lon2 = Double.parseDouble(logitude2);
        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        result = distance;
        Log.i("LOG", "distancia: " + distance);
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }*/


    public Double getLatitudeUsuario() {
        return latitudeUsuario;
    }

    public void setLatitudeUsuario(Double latitudeUsuario) {
        this.latitudeUsuario = latitudeUsuario;
    }

    public Double getLongitudeUsuario() {
        return longitudeUsuario;
    }

    public void setLongitudeUsuario(Double longitudeUsuario) {
        this.longitudeUsuario = longitudeUsuario;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }


}