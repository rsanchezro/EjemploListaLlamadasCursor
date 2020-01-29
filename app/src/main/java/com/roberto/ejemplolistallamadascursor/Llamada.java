package com.roberto.ejemplolistallamadascursor;

public class Llamada {
    private String foto;// CACHED_PHOTO_URI
    private String nombre;//  CACHED_NAMED
    private String fecha;// DATE
    private String numero;//NUMBER
    private Long duracion;//DURATION
    public Llamada(){}
    public Llamada(String foto, String nombre, String fecha, String numero, Long duracion) {
        this.foto = foto;
        this.nombre = nombre;
        this.fecha = fecha;
        this.numero = numero;
        this.duracion = duracion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }
}

