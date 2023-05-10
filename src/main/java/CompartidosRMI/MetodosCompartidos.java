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
    
    Integer getObrExterior() throws RemoteException;
    Integer getObrInterior() throws RemoteException;
    //Integer getSoldInstruccion() throws RemoteException;
    
}
