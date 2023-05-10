/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CompartidosRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author lucas
 */
public interface MetodosCompartidos extends Remote{
    
    Integer getNObrExterior() throws RemoteException;
    Integer getNObrInterior() throws RemoteException;
    Integer getNSoldInstruccion() throws RemoteException;
    Integer getNSoldDefendiendo() throws RemoteException;
    Integer getNCriaComedor() throws RemoteException;
    Integer getNCriaRefugio() throws RemoteException;
    void generarInsectoInvasor() throws RemoteException;
    boolean comprobarAmenaza() throws RemoteException;
    Integer getNSoldTotales() throws RemoteException;
}
