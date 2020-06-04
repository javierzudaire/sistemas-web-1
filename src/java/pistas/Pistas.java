/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pistas;

import java.util.ArrayList;
import pistas.Pista;
/**
 *
 * @author Administrator
 */


public class Pistas {
    ArrayList<Pista> p = new ArrayList<>();
    Pista p1 = new Pista(1,1,1);
    Pista p2 = new Pista(2,1,2);
    Pista p3 = new Pista(3,1,1);
    Pista p4 = new Pista(4,1,2);
    Pista p5 = new Pista(5,2,1);
    Pista p6 = new Pista(6,2,2);
    Pista p7 = new Pista(7,2,1);
    Pista p8 = new Pista(8,2,2);    
   
    public Pistas(){
         p.add(p1);
         p.add(p2);
         p.add(p3);
         p.add(p4);
         p.add(p5);
         p.add(p6);
         p.add(p7);
         p.add(p8);     
    }
    
    public final int size(){
        return p.size();
    }
    public Pista obtenerPista(int id){
        for(Pista pista : p){
            if(pista.id == id){
                return pista;
            }
        }
        return null;
    }

    public ArrayList<Pista> getP() {
        return p;
    }

    public void setP(ArrayList<Pista> p) {
        this.p = p;
    }
    
    public ArrayList<Pista> pistasPadel(){
            ArrayList<Pista> pistasPadel = new ArrayList<>();
            pistasPadel.add(p5);
            pistasPadel.add(p6);
            pistasPadel.add(p7);
            pistasPadel.add(p8);
            return pistasPadel;
    }
    public ArrayList<Pista> pistasTenis(){
            ArrayList<Pista> pistasTenis = new ArrayList<>();
            pistasTenis.add(p1);
            pistasTenis.add(p2);
            pistasTenis.add(p3);
            pistasTenis.add(p4);
            return pistasTenis;
    }
    
    
}
