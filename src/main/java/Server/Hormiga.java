/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import static Server.Util.formatoId;

/**
 *
 * @author lucas
 */
public abstract class Hormiga extends Thread{

    private int idhormiga;
    private String nombre;
    private Colonia c;
    private String tipo;

    public Hormiga(int idhormiga, Colonia colonia) {
        this.idhormiga = idhormiga;
        this.c = colonia;
    }

    public int getIdhormiga() {
        return idhormiga;
    }

    public void setIdhormiga(int idhormiga) {
        this.idhormiga = idhormiga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Colonia getC() {
        return c;
    }

    public void setC(Colonia c) {
        this.c = c;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
