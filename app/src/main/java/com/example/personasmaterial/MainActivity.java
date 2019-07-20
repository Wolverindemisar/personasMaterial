package com.example.personasmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorPersonas.OnPersonaClickListener{

    private RecyclerView lstOpciones;
    private Intent i;
    private ArrayList<Persona> personas;
    private LinearLayoutManager llm;
    private String db = "Personas";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstOpciones = findViewById(R.id.lstPersona);
        personas = new ArrayList<>();
        final AdaptadorPersonas adapter = new AdaptadorPersonas(personas, this);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lstOpciones.setLayoutManager(llm);
        lstOpciones.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personas.clear();
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        Persona p = snapshot.getValue(Persona.class);
                        personas.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setPersonas(personas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void agregarPersona(View v)
    {
        i = new Intent(MainActivity.this, AgregarPersona.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onPersonaClick(Persona p) {
        Intent i = new Intent(MainActivity.this, DetallePersona.class);
        Bundle b = new Bundle();
        b.putString("id", p.getId());
        b.putString("nombre", p.getNombre());
        b.putString("apellido", p.getApellido());
        b.putInt("foto", p.getFoto());

        i.putExtra("datos", b);
        startActivity(i);
        finish();
    }
}
