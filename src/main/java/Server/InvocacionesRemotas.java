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
    public Integer getNObrExterior(){
        return this.colonia.getNBuscando();
    }
    
    @Override
    public Integer getNObrInterior(){
        return this.colonia.getNObrerasInterior();
    }
    
    @Override 
    public Integer getNSoldInstruccion(){
        return this.colonia.getNSoldadosInstruccion();
    }
    
    @Override 
    public Integer getNSoldDefendiendo(){
        return this.colonia.getNSoldadosDefendiendo();
    }
    
    @Override 
    public Integer getNCriaComedor(){
        return this.colonia.getNCriasComedor();
    }
    
    @Override 
    public Integer getNCriaRefugio(){
        return this.colonia.getNCriasRefugio();
    }
    
    @Override
    public void generarInsectoInvasor(){
        this.colonia.generarInsectoInvasor();
    }
    
    @Override
    public boolean comprobarAmenaza(){
        return this.colonia.comprobarAmenaza();
    }
    
    @Override 
    public Integer getNSoldTotales(){
        return this.colonia.getNSoldadosTotales();
    }
}
