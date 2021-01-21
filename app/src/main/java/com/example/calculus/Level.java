package com.example.calculus;

import java.util.ArrayList;

public class Level implements java.io.Serializable{
    private Generator uno;
    private Generator dos;
    private Generator tres;
    private Generator cuatro;
    private Generator cinco;
    private Generator seis;
    private ArrayList cuentas;

    private boolean unoGana;
    private boolean dosGana;
    private boolean tresGana;
    private boolean cuatroGana;
    private boolean cincoGana;
    private boolean seisGana;

    private int llega;
    private int puntuacionSobreSeis;
    private int comodines;


    public Level(int difficulty) {
        this.uno = new Generator(difficulty);
        this.dos = new Generator(difficulty);
        this.tres = new Generator(difficulty);
        this.cuatro = new Generator(difficulty);
        this.cinco = new Generator(difficulty);
        this.seis = new Generator(difficulty);
        cuentas=new ArrayList();
        cuentas.add(uno);
        cuentas.add(dos);
        cuentas.add(tres);
        cuentas.add(cuatro);
        cuentas.add(cinco);
        cuentas.add(seis);
        this.llega=1;
        puntuacionSobreSeis=0;
        comodines=4-difficulty;
    }

    public Generator getUno() {
        return uno;
    }

    public void setUno(Generator uno) {
        this.uno = uno;
    }

    public Generator getDos() {
        return dos;
    }

    public void setDos(Generator dos) {
        this.dos = dos;
    }

    public Generator getTres() {
        return tres;
    }

    public void setTres(Generator tres) {
        this.tres = tres;
    }

    public Generator getCuatro() {
        return cuatro;
    }

    public void setCuatro(Generator cuatro) {
        this.cuatro = cuatro;
    }

    public Generator getCinco() {
        return cinco;
    }

    public void setCinco(Generator cinco) {
        this.cinco = cinco;
    }

    public Generator getSeis() {
        return seis;
    }

    public void setSeis(Generator seis) {
        this.seis = seis;
    }

    public ArrayList<Generator> getCuentas() {
        return cuentas;
    }
    public ArrayList getCuentasNeutras() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Generator> cuentas) {
        this.cuentas = cuentas;
    }

    public void setCuentasNeutral(ArrayList cuentas) {
        this.cuentas = cuentas;
    }

    public boolean isUnoGana() {
        return unoGana;
    }

    public void setUnoGana(boolean unoGana) {
        this.unoGana = unoGana;
    }

    public boolean isDosGana() {
        return dosGana;
    }

    public void setDosGana(boolean dosGana) {
        this.dosGana = dosGana;
    }

    public boolean isTresGana() {
        return tresGana;
    }

    public void setTresGana(boolean tresGana) {
        this.tresGana = tresGana;
    }

    public boolean isCuatroGana() {
        return cuatroGana;
    }

    public void setCuatroGana(boolean cuatroGana) {
        this.cuatroGana = cuatroGana;
    }

    public boolean isCincoGana() {
        return cincoGana;
    }

    public void setCincoGana(boolean cincoGana) {
        this.cincoGana = cincoGana;
    }

    public boolean isSeisGana() {
        return seisGana;
    }

    public void setSeisGana(boolean seisGana) {
        this.seisGana = seisGana;
    }

    public int getLlega() {
        return llega;
    }

    public void setLlega(int llega) {
        this.llega = llega;
    }

    public int getPuntuacionSobreSeis() {
        return puntuacionSobreSeis;
    }

    public void setPuntuacionSobreSeis(int puntuacionSobreSeis) {
        this.puntuacionSobreSeis = puntuacionSobreSeis;
    }

    public int getComodines() {
        return comodines;
    }

    public void setComodines(int comodines) {
        this.comodines = comodines;
    }

    public void useComodin(){
        if(comodines>0){
            comodines--;
        }
    }

    public void prueba(int resultado){

        if(llega<=6){

            ArrayList<Generator> prueba=new ArrayList<>();
            for(Object o:cuentas){
                prueba.add((Generator) o);
            }

            if(prueba.get(llega-1).checkAnswer(resultado)){
                puntuacionSobreSeis++;
            }
            llega++;
        }



    }


}
