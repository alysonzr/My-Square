package com.example.mypark.DataBase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mypark.Praca;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PesquisaBD extends AppCompatActivity {

    private ListView listVPesquisa;
    FirebaseFirestore fireStore;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public List<Praca> listPracas = new ArrayList<Praca>();
    public ArrayAdapter<Praca> arrayAdapterPraca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireStore = FirebaseFirestore.getInstance();


    }

    public void obtendoDados(){
        fireStore.collection("Parks").document("1").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    String endereco = documentSnapshot.getString("Endereco");
                    List<Map> facilidades = (List<Map>) documentSnapshot.get("Facilidades");
                    String nome = documentSnapshot.getString("Nome");


                }

            }
        });
    }



}
