package com.example.personasmaterial;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class AgregarPersona extends AppCompatActivity {

    private ArrayList<Integer>fotos;
    private EditText nombre;
    private EditText apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
    }

    public void guardar(View v)
    {
        String nom;
        String apel;
        String id;
        int foto;

        id = Datos.getID();
        nom = nombre.getText().toString();
        apel = apellido.getText().toString();
        foto = this.fotoAleatoria();
        Persona p = new Persona(id, foto, nom, apel);
        p.guardar();

        Limpiar();
        Snackbar.make(v, getString(R.string.mensaje), Snackbar.LENGTH_SHORT).show();
    }

    public void limpiar (View v)
    {
        Limpiar();
    }

    public void Limpiar()
    {
        nombre.setText("");
        apellido.setText("");
        nombre.requestFocus();
    }

    public int fotoAleatoria()
    {
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(this.fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public void onBackPressed()
    {
        finish();
        Intent i = new Intent(AgregarPersona.this, MainActivity.class);
        startActivity(i);
    }
}
