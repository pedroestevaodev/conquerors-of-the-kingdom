package com.territorio;

import com.exercito.Tropa;

import java.util.ArrayList;
import java.util.List;

public class Reino {
    private int recursos;
    private List<Edificio> edificios;
    private List<Tropa> tropas;

    public Reino() {
        this.recursos = 100;
        this.edificios = new ArrayList<>();
        this.tropas = new ArrayList<>();
    }

    public int getRecursos() {
        return recursos;
    }

    public void setRecursos(int recursos) {
        this.recursos = recursos;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }

    public List<Tropa> getTropas() {
        return tropas;
    }

    public void setTropas(List<Tropa> tropas) {
        this.tropas = tropas;
    }
}
