package com.example.mypark;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mypark.DataBase.BDHelper;


public class RegisterActivity extends AppCompatActivity {

    EditText txtNomeRegistro,txtEmailRegistro,txtSenhaRegistro;
    Button btnRegistrarNovo;

    BDHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         db = new BDHelper(this);


        txtNomeRegistro=(EditText)findViewById(R.id.txtNomeRegistro);
        txtEmailRegistro=(EditText)findViewById(R.id.txtEmailRegistro);
        txtSenhaRegistro=(EditText)findViewById(R.id.txtSenhaRegistro);

        btnRegistrarNovo=(Button)findViewById(R.id.btnRegistrarNovo);

        btnRegistrarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtNomeRegistro.getText().toString();
                String senha = txtSenhaRegistro.getText().toString();
                String email = txtEmailRegistro.getText().toString();

                if (nome.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Nome nao inserido, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (senha.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Campo senha nao deve ser vazio, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Campo email nao deve ser vazio, tente novamente", Toast.LENGTH_SHORT).show();;
                } else{
                    //tudo ok
                    long res = db.CriarUtilizador(nome,senha);
                    if(res>0){
                        Toast.makeText(RegisterActivity.this, "Registro OK,", Toast.LENGTH_SHORT).show();;
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registro Invalido!, Tente Novamente.", Toast.LENGTH_SHORT).show();;
                    }
                }
            }
        });
    }
}


