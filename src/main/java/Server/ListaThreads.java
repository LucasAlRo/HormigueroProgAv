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
    private ArrayList<Hormiga> lista;
    private JTextField tf;
    
    public ListaThreads(JTextField tf)
    {
        lista=new ArrayList<Hormiga>();
        this.tf=tf;
    }
    
    public synchronized void meter(Hormiga h) throws InterruptedException
    {
        lista.add(h);
        imprimir();
    }
    
    public synchronized void sacar(Hormiga h) throws InterruptedException
    {
        lista.remove(h);
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
        return lista.size();
    }     
}

