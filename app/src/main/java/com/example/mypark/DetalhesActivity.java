package com.example.mypark;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.example.mypark.R.id.map;

public class DetalhesActivity extends AppCompatActivity implements OnMapReadyCallback {


    TextView txtEndereco, txtNomePraca, nomeInstalacoes, txtDistancia;
    ViewPager viewPager;
    Button btnRota;
    String nomePraca;
    Double latitudeDevice;
    Double longitudeDevice;

    Double latitudePraca;
    Double longitudePraca;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtEndereco = findViewById(R.id.txtEndereco);
        txtNomePraca = findViewById(R.id.txtNomePraca);
        viewPager = findViewById(R.id.view_pager);
        txtDistancia = findViewById(R.id.txtDistancia);
        nomeInstalacoes = findViewById(R.id.nomeInstalacoes);
        btnRota = findViewById(R.id.btnRota);
        mostraDadosNaTela();


    }

    private void mostraDadosNaTela() {
        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("nome");
        String endereco = extras.getString("endereco");
        ArrayList imagem = (ArrayList) extras.get("imagem");
        String icon = extras.getString("icon");
        ArrayList instalaDetalhes = (ArrayList) extras.get("instalaDetalhes");
        String instalacoes = extras.getString("Instalacoes");
        Double latitude = extras.getDouble("latitude");
        Double longitude = extras.getDouble("longitude");

        Double latitudeDevice = extras.getDouble("latitudeDevice");
        Double longitudeDevice = extras.getDouble("longitudeDevice");

        setLatitudeDevice(latitudeDevice);
        setLongitudeDevice(longitudeDevice);

        setLatitudePraca(latitude);
        setLongitudePraca(longitude);
        setNomePraca(nome);

        ViewPagerAdapter adapterPager = new ViewPagerAdapter(this, imagem);
        String[] instala = instalacoes.split(Pattern.quote(","));
        ArrayList<String> lista1 = new ArrayList<String>();
        ArrayList<String> i = convertStringArrayToArraylist(instala);
        for (String o : i) {
            lista1.add(o.toString());
        }

        ListView listView = (ListView) findViewById(R.id.lisv);
        PracaDetalhesAdapter adapter = new PracaDetalhesAdapter(this, lista1);
        listView.setAdapter(adapter);

        viewPager.setAdapter(adapterPager);
        txtEndereco.setText("Endereço: " + endereco);
        txtNomePraca.setText("Nome: " + nome);
        double dist = calculaDistancia(getLatitudePraca().doubleValue(), getLongitudePraca().doubleValue(),getLatitudeDevice().doubleValue(), getLongitudeDevice().doubleValue());
        txtDistancia.setText("Distancia: "+dist);

        double distFormatada = (((int) Math.ceil(dist * 10)) % 10);
        txtDistancia.setText("Distancia: "+distFormatada+"Km");

        btnRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = String.format(Locale.getDefault(),  "google.navigation:q="+getLatitudePraca()+"+"+getLongitudePraca());
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }


    public static ArrayList<String> convertStringArrayToArraylist(String[] strArr) {
        ArrayList<String> stringList = new ArrayList<String>();
        for (String s : strArr) {
            stringList.add(s);
        }
        return stringList;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double  latitude = getLatitudePraca().doubleValue();
        double  longitude = getLongitudePraca().doubleValue();
        LatLng cidade = new LatLng(latitude,longitude );
        mMap.addMarker(new MarkerOptions().position(cidade).title(getNomePraca()));
        float zoomLevel = 15.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cidade, zoomLevel));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }



    private static double calculaDistancia(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        double result = dist = dist * 1.609344;

        return result;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    public Double getLatitudeDevice() {
        return latitudeDevice;
    }

    public void setLatitudeDevice(Double latitudeDevice) {
        this.latitudeDevice = latitudeDevice;
    }

    public Double getLongitudeDevice() {
        return longitudeDevice;
    }

    public void setLongitudeDevice(Double longitudeDevice) {
        this.longitudeDevice = longitudeDevice;
    }

    public Double getLatitudePraca() {
        return latitudePraca;
    }

    public void setLatitudePraca(Double latitudePraca) {
        this.latitudePraca = latitudePraca;
    }

    public Double getLongitudePraca() {
        return longitudePraca;
    }

    public void setLongitudePraca(Double longitudePraca) {
        this.longitudePraca = longitudePraca;
    }

    public String getNomePraca() {
        return nomePraca;
    }

    public void setNomePraca(String nomePraca) {
        this.nomePraca = nomePraca;
    }
}
