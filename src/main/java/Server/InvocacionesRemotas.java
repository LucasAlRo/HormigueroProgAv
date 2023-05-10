/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import CompartidosRMI.MetodosCompartidos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author lucas
 */
public class InvocacionesRemotas  extends UnicastRemoteObject implements MetodosCompartidos{
    
    private Colonia colonia;
    
    public InvocacionesRemotas(Colonia c) throws RemoteException{
        this.colonia  =  c;
    }
    
    @Override 
    public Integer getObrExterior(){
        return colonia.getBuscando().getTamano();
    }
    
    @Override
    public Integer getObrInterior(){
        return (colonia.getObrerasInterior());
    }
    
}
