package com.example.mypark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.firestore.FirebaseFirestore;


//PAGINA INICIAL APOS USUARIO LOGAR
public class LoginActivity extends AppCompatActivity {


    Button btnPesquisar;
    CheckBox checkAcademia,checkDog,checkSkate,checkAreaCrianca,checkBike,checkCorrida;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnPesquisar = (Button)findViewById(R.id.btnPesquisar);

        checkBike = (CheckBox) findViewById(R.id.checkBike);
        checkAreaCrianca = (CheckBox) findViewById(R.id.checkAreaInfantil);
        checkCorrida = (CheckBox)findViewById(R.id.checkCorrida);
        checkAcademia = (CheckBox)findViewById(R.id.checkAcademia);
        checkDog = (CheckBox)findViewById(R.id.checkDog);
        checkSkate = (CheckBox)findViewById(R.id.checkSkate);

        //Recebendo nome do usuario da main
        Intent i = getIntent();
        Bundle p = i.getExtras();
        String nome = p.getString("chave_nome");
        setTitle("Bem Vindo, " + nome);



        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facilits = "";
               final Intent i = new Intent(LoginActivity.this, OpcoesDePraca.class);
                if(!checkBike.isChecked() && !checkAreaCrianca.isChecked() && !checkCorrida.isChecked() && !checkAcademia.isChecked() &&  !checkDog.isChecked() && !checkSkate.isChecked() ){


                }

                if(checkSkate.isChecked()){
                    facilits = "skatee";
                    i.putExtra(facilits, true);

                }
                if(checkAreaCrianca.isChecked()){
                    facilits = "AreaCrianca";
                    i.putExtra(facilits, true);
                }
                if(checkCorrida.isChecked()){
                    facilits = "Corrida";
                    i.putExtra(facilits, true);
                }
                if(checkDog.isChecked()){
                    facilits = "areaDog";
                    i.putExtra(facilits, true);
                }

                startActivity(i);

            }


        });

    }


}
