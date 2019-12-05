package com.example.mypark;

import java.util.ArrayList;
import java.util.List;

public class AdapterDetalhes {

    public List<String> nomeFacilidades;
    public String icon;

    public AdapterDetalhes(List nomeFacilidades, String icon) {
        this.nomeFacilidades = nomeFacilidades;
        this.icon = icon;
    }

    public List getNomeFacilidades() {
        return nomeFacilidades;
    }

    public void setNomeFacilidades(List nomeFacilidades) {
        this.nomeFacilidades = nomeFacilidades;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
