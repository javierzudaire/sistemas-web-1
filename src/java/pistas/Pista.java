/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pistas;

import reservas.Reserva;

/**
 *
 * @author Administrator
 */
public class Pista {
    public int id;
    public String deporte,tipo; // 1 para tenis y 2 para padel. 1 para descubiertas y 2 para descubiertas

    public Pista(int id,int deporte,int tipo){
        this.id = id;
        setDeporte(deporte);
        setTipo(tipo);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(int deporte) {
        if(deporte==1){
            this.deporte = "Tenis";
        }else if(deporte==2){
            this.deporte = "Padel";
        }else{
            System.err.print("Error al asignar tipo a pista");
        }   
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        if(tipo==1) this.tipo ="Descubierta";
        if(tipo==2) this.tipo ="Cubierta";
    }

    public int getDeporteId(){
        if(this.deporte.equals("Tenis")) return 1;
        else{
            return 2;
        }
    }
    public boolean isPadel(){
        return deporte=="Padel";
    }
    
    public boolean isTennis(){
        return deporte=="Tenis";
    }

    public int getIdTipo() {
        if(tipo=="Descubierta") return 1;
        if(tipo=="Cubierta") return 2;
        return 0;
    }
    
    
}
