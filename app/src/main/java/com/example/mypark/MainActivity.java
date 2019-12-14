package com.example.mypark;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.mypark.DataBase.BDHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {


    EditText txtNome, txtSenha;
    Button btnLogar;
    BDHelper bd;
    TextView btnRegistrar;

        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BDHelper(this);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnRegistrar = (TextView) findViewById(R.id.btnRegistrar);
       // inicializarFirebase();


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtNome.getText().toString();
                String senha = txtSenha.getText().toString();

                if (nome.equals("")) {
                    Toast.makeText(MainActivity.this, "Nome nao inserido!", Toast.LENGTH_SHORT).show();
                } else if (senha.equals("")) {
                    Toast.makeText(MainActivity.this, "Senha nao inserida!", Toast.LENGTH_SHORT).show();
                } else {
                    //tudo ok
                    String res = bd.ValidarLogin(nome, senha);
                    if (res.equals("OK")) {
                        Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        Bundle p = new Bundle();
                        p.putString("chave_nome", nome);
                        i.putExtras(p);
                        startActivity(i);

                    } else {
                        Toast.makeText(MainActivity.this, "Login Incorreto!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }


}




