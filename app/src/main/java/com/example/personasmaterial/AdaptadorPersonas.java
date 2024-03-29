package com.example.personasmaterial;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPersonas extends RecyclerView.Adapter<AdaptadorPersonas.PersonaViewHolder>{

    private ArrayList<Persona> personas;
    private OnPersonaClickListener clickListener;

    public AdaptadorPersonas(ArrayList<Persona>personas, OnPersonaClickListener clickListener)
    {
        this.personas = personas;
        this.clickListener = clickListener;
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_persona, viewGroup ,false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder personaViewHolder, int i) {
        final Persona p = personas.get(i);
        personaViewHolder.foto.setImageResource(p.getFoto());
        personaViewHolder.nombre.setText(p.getNombre());
        personaViewHolder.apellido.setText(p.getApellido());

        personaViewHolder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPersonaClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nombre;
        private TextView apellido;
        private View v;

        public PersonaViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.foto);
            nombre = v.findViewById(R.id.lblNombre);
            apellido = v.findViewById(R.id.lblApellido);
        }
    }

    public interface OnPersonaClickListener
    {
        void onPersonaClick(Persona p);
    }
}
