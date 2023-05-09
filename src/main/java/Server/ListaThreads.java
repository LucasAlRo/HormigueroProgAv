/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class ListaThreads {
    ArrayList<Hormiga> lista;
    JTextField tf;
    int tamano;
    
    public ListaThreads(JTextField tf)
    {
        lista=new ArrayList<Hormiga>();
        this.tf=tf;
        this.tamano=0;
    }
    
    public synchronized void meter(Hormiga h) throws InterruptedException
    {
        lista.add(h);
        tamano++;
        imprimir();
    }
    
    public synchronized void sacar(Hormiga h) throws InterruptedException
    {
        lista.remove(h);
        tamano--;
        imprimir();
    }
    
    public synchronized void imprimir()
    {
        String contenido="";
        for(int i=0; i<lista.size(); i++)
        {
           contenido=contenido+lista.get(i).getNombre()+" ";
        }
        tf.setText(contenido);
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    
}

