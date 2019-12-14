package com.example.mypark;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class DetalhesActivity extends AppCompatActivity implements OnMapReadyCallback {


    TextView txtEndereco, txtNomePraca, nomeInstalacoes, txtDistancia;
    ViewPager viewPager;
    String nomePraca;
    Double latitudeDevice;
    Double longitudeDevice;
    BottomNavigationView navView;
    Double latitudePraca;
    Double longitudePraca;
    Button btn_ver_comentarios;
    private GoogleMap mMap;
    public static int EARTH_RADIUS_KM = 6371;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
              /*  case R.id.voltar:
                    Intent  i = new Intent(DetalhesActivity.this, OpcoesDePraca.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    return true;*/
                case R.id.menu:
                    Intent in = new Intent(DetalhesActivity.this, LoginActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(in);
                    return true;
                case R.id.sair:
                    Intent e = new Intent(DetalhesActivity.this, MainActivity.class);
                    startActivity(e);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtEndereco = findViewById(R.id.txtEndereco);
        txtNomePraca = findViewById(R.id.txtNomePraca);
        viewPager = findViewById(R.id.view_pager);
        txtDistancia = findViewById(R.id.txtDistancia);
        nomeInstalacoes = findViewById(R.id.nomeInstalacoes);
        final Button btn = findViewById(R.id.btn_ver_comentarios);

        mostraDadosNaTela();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                ArrayList comentarios = (ArrayList) extras.get("comentarios");
                ArrayList coment = comentarios;
                Intent i = new Intent(DetalhesActivity.this, ActivityComentarios.class);
                i.putExtra("coment", coment);
                startActivity(i);
            }
        });

    }

    private void mostraDadosNaTela() {
        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("nome");
        String endereco = extras.getString("endereco");
        ArrayList imagem = (ArrayList) extras.get("imagem");
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
        txtNomePraca.setText(nome);


        double firstLatToRad = Math.toRadians(latitude);
        double secondLatToRad = Math.toRadians(latitudeDevice);
        double deltaLongitudeInRad = Math.toRadians(longitudeDevice - longitude);
        double distancia = formatNumber(firstLatToRad, secondLatToRad, deltaLongitudeInRad);
        txtDistancia.setText("Distancia:  " + distancia + " Km Aproximadamente");
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
        double  latitudeUser = getLatitudeDevice().doubleValue();
        double  longitudeUser = getLongitudeDevice().doubleValue();
        LatLng praca = new LatLng(latitude,longitude );
        LatLng usuario = new LatLng(latitudeUser,longitudeUser );
        mMap.addMarker(new MarkerOptions().position(praca).title(getNomePraca()));
        mMap.addMarker(new MarkerOptions().position(usuario).title("Você esta Aqui"));

        float zoomLevel = 13.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(praca, zoomLevel));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);


    }


    public static double formatNumber(double firstLatToRad, double secondLatToRad, double deltaLongitudeInRad) {
        double dist =  Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad) *
                Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad) *
                Math.sin(secondLatToRad))* EARTH_RADIUS_KM;

        double arredondado = dist;
            int casas = 2;
            int ceilOrFloor = 0;
            arredondado *= (Math.pow(10, casas));
             if (ceilOrFloor == 0) {
                arredondado = Math.ceil(arredondado);
            } else {
                arredondado = Math.floor(arredondado);
            }
                 arredondado /= (Math.pow(10, casas));
            return arredondado;

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
