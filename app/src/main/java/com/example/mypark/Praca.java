package com.example.mypark;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Praca {

    private String uid;
    private String nome;
    private String facilidades;
    private String endereco;
    private ArrayList imagem;
    private String icon;
    private Number latitude;
    private Number longitude;
    private double distancia;
    private ArrayList comentarios;


    public Praca(String uid, String nome, String facilidades, String endereco, ArrayList imagem, Number latitude, Number longitude, double distancia,ArrayList comentarios) {
        this.uid = uid;
        this.nome = nome;
        this.facilidades = facilidades;
        this.endereco = endereco;
        this.imagem = imagem;
        this.icon = icon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distancia = distancia;
        this.comentarios = comentarios;

    }

    public ArrayList getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList comentarios) {
        this.comentarios = comentarios;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList getImagem() {
        return imagem;
    }

    public void setImagem(ArrayList imagem) {
        this.imagem = imagem;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getFacilidades() {
        return facilidades;
    }

    public void setFacilidades(String facilidades) {
        this.facilidades = facilidades;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
