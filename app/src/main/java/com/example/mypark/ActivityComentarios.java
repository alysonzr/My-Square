package com.example.mypark;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ActivityComentarios extends AppCompatActivity {

    ListView lista_comentarios;
    FirebaseFirestore fireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        //private static final String TAG = "ActivityComentarios";
        EditText comenariosUsuario = findViewById(R.id.idComentario);

        fireStore = FirebaseFirestore.getInstance();


        Bundle extras = getIntent().getExtras();
        ArrayList<String> comentarios = (ArrayList) extras.get("coment");


        ArrayList<String> listaComentarios = new ArrayList<String>();
        for (String o : comentarios) {
            listaComentarios.add(o);
        }

        ListView listView = (ListView) findViewById(R.id.list_comentarios);
        ComentarioAdapter adapter = new ComentarioAdapter(this, listaComentarios);
        listView.setAdapter(adapter);

        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        //  ArrayList<String> comentarios = new ArrayList<>();
        // comentarios.add(comenariosUsuario.toString());

        user.put("first", "Alan");
        user.put("middle", "Mathison");
        user.put("last", "Turing");
        user.put("born", 1912);

        // Add a new document with a generated ID
      /*  fireStore.collection("Parks").document("PracasGravatai").collection("AreaCrianca").document("Comentarios")
                .update(comenariosUsuario.toString(), new Object())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Log", "Error adding document", e);
                    }
                });*/


       /* final ArrayList<Praca> pracas = new ArrayList<Praca>();
        fireStore.collection("Parks").document("PracasGravatai").collection("AreaCrianca").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String uid = (document.getId());


    }


}*/
    }
}