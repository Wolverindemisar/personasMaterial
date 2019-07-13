package com.example.personasmaterial;

public class Persona {

    private String id;
    private int foto;
    private String nombre;
    private String apellido;

    public Persona(){ }

    public Persona(String id, int foto, String nombre, String apellido)
    {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void guardar()
    {
        Datos.agregar(this);
    }

    public void eliminar()
    {
        Datos.eliminar(this);
    }

    public void editar()
    {
        Datos.editar(this);
    }
}
